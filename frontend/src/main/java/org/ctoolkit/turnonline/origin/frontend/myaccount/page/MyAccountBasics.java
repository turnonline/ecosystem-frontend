package org.ctoolkit.turnonline.origin.frontend.myaccount.page;

import biz.turnonline.ecosystem.account.client.model.Account;
import biz.turnonline.ecosystem.account.client.model.AccountPostalAddress;
import biz.turnonline.ecosystem.account.client.model.Country;
import biz.turnonline.ecosystem.account.client.model.LegalForm;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.string.Strings;
import org.ctoolkit.turnonline.origin.frontend.identity.Role;
import org.ctoolkit.turnonline.origin.frontend.model.CountriesModel;
import org.ctoolkit.turnonline.origin.frontend.model.CountryRenderer;
import org.ctoolkit.turnonline.origin.frontend.model.LegalFormCodeModel;
import org.ctoolkit.turnonline.origin.frontend.model.LegalFormListModel;
import org.ctoolkit.turnonline.origin.frontend.model.LegalFormRenderer;
import org.ctoolkit.turnonline.origin.frontend.model.MyAccountModel;
import org.ctoolkit.turnonline.origin.frontend.myaccount.event.AccountUpdateEvent;
import org.ctoolkit.turnonline.origin.frontend.myaccount.model.CompanyDomicileModel;
import org.ctoolkit.turnonline.origin.frontend.myaccount.model.PersonalAddressCountryModel;
import org.ctoolkit.turnonline.origin.frontend.myaccount.model.PostalAddressCountryModel;
import org.ctoolkit.wicket.standard.identity.behavior.FirebaseAppInit;
import org.ctoolkit.wicket.standard.markup.html.form.ajax.IndicatingAjaxButton;
import org.ctoolkit.wicket.standard.markup.html.form.ajax.IndicatingAjaxDropDown;
import org.ctoolkit.wicket.standard.model.I18NResourceModel;
import org.ctoolkit.wicket.turnonline.identity.IdentityOptions;
import org.ctoolkit.wicket.turnonline.markup.html.page.DecoratedPage;
import org.ctoolkit.wicket.turnonline.myaccount.event.ToggleCompanyPersonChangeEvent;
import org.ctoolkit.wicket.turnonline.myaccount.panel.CompanyAddressPanel;
import org.ctoolkit.wicket.turnonline.myaccount.panel.CompanyBasicInfo;
import org.ctoolkit.wicket.turnonline.myaccount.panel.CompanyPersonSwitcher;
import org.ctoolkit.wicket.turnonline.myaccount.panel.PersonalAddressPanel;
import org.ctoolkit.wicket.turnonline.myaccount.panel.PersonalDataPanel;
import org.ctoolkit.wicket.turnonline.myaccount.panel.PostalAddressPanel;
import org.ctoolkit.wicket.turnonline.myaccount.panel.SimplifiedContactFieldSet;

import javax.inject.Inject;
import java.util.Map;

/**
 * The page dedicated for basic account settings.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
@AuthorizeInstantiation( {Role.STANDARD} )
public class MyAccountBasics
        extends DecoratedPage<Account>
{
    private static final long serialVersionUID = -1303189991396080065L;

    private I18NResourceModel titleModel = new I18NResourceModel( "title.my-account" );

    @Inject
    private IdentityOptions identityOptions;

    public MyAccountBasics()
    {
        add( new FirebaseAppInit( identityOptions ) );

        final MyAccountModel accountModel = new MyAccountModel();
        final IModel<Map<String, Country>> countriesModel = new CountriesModel();

        setModel( accountModel );

        // form
        Form<Account> form = new Form<Account>( "form", accountModel )
        {
            private static final long serialVersionUID = -938924956863034465L;

            @Override
            protected void onSubmit()
            {
                Account account = getModelObject();
                send( getPage(), Broadcast.BREADTH, new AccountUpdateEvent( account ) );
            }
        };
        add( form );

        PropertyModel<Boolean> companyModel = new PropertyModel<>( accountModel, "company" );
        form.add( new CompanyPersonSwitcher( "isCompanyRadioGroup", companyModel ) );

        // account email fieldset
        form.add( new Label( "email", new PropertyModel<>( accountModel, "email" ) ) );

        // company basic info
        final CompanyBasicInfo<Account> basicInfo = new CompanyBasicInfo<Account>( "companyData", accountModel )
        {
            private static final long serialVersionUID = -2992960490517951459L;

            @Override
            protected DropDownChoice<LegalForm> provideLegalForm( String componentId )
            {
                LegalFormListModel choices = new LegalFormListModel();
                return new IndicatingAjaxDropDown<>( componentId,
                        new LegalFormCodeModel( accountModel, "legalForm", choices ),
                        choices, new LegalFormRenderer() );
            }

            @Override
            protected void onConfigure()
            {
                super.onConfigure();
                Account account = getModelObject();
                this.setVisible( account.getCompany() );
            }
        };
        form.add( basicInfo );

        // company basic info panel behaviors
        basicInfo.addLegalForm( new OnChangeAjaxBehavior()
        {
            private static final long serialVersionUID = 6948210639258798921L;

            @Override
            protected void onUpdate( AjaxRequestTarget target )
            {
            }
        } );

        basicInfo.addVatId( new Behavior()
        {
            private static final long serialVersionUID = 100053137512632023L;

            @Override
            public void onConfigure( Component component )
            {
                super.onConfigure( component );
                Account account = basicInfo.getModelObject();
                boolean visible = account == null || account.getVatPayer();
                component.setVisible( visible );
            }
        } );

        final TextField taxId = basicInfo.getTaxId();
        final TextField vatId = basicInfo.getVatId();
        final CheckBox vatPayer = basicInfo.getVatPayer();

        basicInfo.addVatPayer( new AjaxFormSubmitBehavior( OnChangeAjaxBehavior.EVENT_NAME )
        {
            private static final long serialVersionUID = -1238082494184937003L;

            @Override
            protected void onSubmit( AjaxRequestTarget target )
            {
                Account account = ( Account ) basicInfo.getDefaultModelObject();
                String rawTaxIdValue = taxId.getRawInput();

                if ( rawTaxIdValue != null && Strings.isEmpty( account.getVatId() ) )
                {
                    // VAT state prefix proposal
                    String state = account.getDomicile();
                    state = state == null ? "" : state.toUpperCase();
                    //noinspection unchecked
                    vatId.getModel().setObject( state + rawTaxIdValue );
                }

                // must be set manually as getDefaultProcessing() returns false
                vatPayer.setModelObject( !account.getVatPayer() );

                if ( target != null )
                {
                    target.add( vatId.getParent() );
                }
            }

            @Override
            public boolean getDefaultProcessing()
            {
                return false;
            }
        } );

        // personal data panel
        PersonalDataPanel<Account> personalData = new PersonalDataPanel<Account>( "personalData", accountModel )
        {
            private static final long serialVersionUID = -2808922906891760016L;

            @Override
            protected void onConfigure()
            {
                super.onConfigure();
                Account account = getModelObject();
                this.setVisible( !account.getCompany() );
            }
        };
        form.add( personalData );

        // personal address panel
        PersonalAddressPanel<Account> address = new PersonalAddressPanel<Account>( "personalAddress", accountModel )
        {
            private static final long serialVersionUID = 3481146248010938807L;

            @Override
            protected DropDownChoice<Country> provideCountry( String componentId )
            {
                return new IndicatingAjaxDropDown<>( componentId,
                        new PersonalAddressCountryModel( accountModel, countriesModel ),
                        new CountryRenderer(),
                        countriesModel );
            }

            @Override
            protected void onConfigure()
            {
                super.onConfigure();
                Account account = getModelObject();
                this.setVisible( !account.getCompany() );
            }
        };
        form.add( address );

        address.addCountry( new OnChangeAjaxBehavior()
        {
            private static final long serialVersionUID = -1016447969591778948L;

            @Override
            protected void onUpdate( AjaxRequestTarget target )
            {
            }
        } );

        // company address panel
        CompanyAddressPanel<Account> companyAddress;
        companyAddress = new CompanyAddressPanel<Account>( "companyAddress", accountModel, false, false )
        {
            private static final long serialVersionUID = -6760545061622186549L;

            @Override
            protected DropDownChoice<Country> provideCountry( String componentId )
            {
                return new IndicatingAjaxDropDown<>( componentId,
                        new CompanyDomicileModel( accountModel, countriesModel ),
                        new CountryRenderer(),
                        countriesModel );
            }

            @Override
            protected void onConfigure()
            {
                super.onConfigure();
                Account account = getModelObject();
                this.setVisible( account.getCompany() );
            }
        };
        form.add( companyAddress );

        companyAddress.addCountry( new OnChangeAjaxBehavior()
        {
            private static final long serialVersionUID = -5476413125490349124L;

            @Override
            protected void onUpdate( AjaxRequestTarget target )
            {
            }
        } );

        IModel<AccountPostalAddress> postalAddressModel = new PropertyModel<>( accountModel, "postalAddress" );
        IModel<Boolean> hasAddress = new PropertyModel<>( accountModel, "hasPostalAddress" );
        PostalAddressPanel<AccountPostalAddress> postalAddress;
        postalAddress = new PostalAddressPanel<AccountPostalAddress>( "postal-address", postalAddressModel, hasAddress )
        {
            private static final long serialVersionUID = -930960688138308527L;

            @Override
            protected DropDownChoice<Country> provideCountry( String componentId )
            {
                return new IndicatingAjaxDropDown<>( componentId,
                        new PostalAddressCountryModel( accountModel, countriesModel ),
                        new CountryRenderer(),
                        countriesModel );
            }
        };
        form.add( postalAddress );

        postalAddress.addStreet( new OnChangeAjaxBehavior()
        {
            private static final long serialVersionUID = 4050800366443676166L;

            @Override
            protected void onUpdate( AjaxRequestTarget target )
            {
            }
        } );

        PropertyModel<Object> billingContactModel = PropertyModel.of( accountModel, "billingContact" );
        form.add( new SimplifiedContactFieldSet<>( "contact", billingContactModel ) );
        // save button
        form.add( new IndicatingAjaxButton( "save", new I18NResourceModel( "button.save" ), form ) );
    }

    @Override
    public void onEvent( IEvent<?> event )
    {
        super.onEvent( event );

        if ( event.getPayload() instanceof ToggleCompanyPersonChangeEvent )
        {
            ToggleCompanyPersonChangeEvent payload = ( ToggleCompanyPersonChangeEvent ) event.getPayload();
            Component form = get( "form" );
            payload.getTarget().add( form );
        }
    }

    @Override
    public IModel<String> getPageTitle()
    {
        return titleModel;
    }
}
