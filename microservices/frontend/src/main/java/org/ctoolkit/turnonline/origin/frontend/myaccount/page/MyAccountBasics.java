package org.ctoolkit.turnonline.origin.frontend.myaccount.page;

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
import org.ctoolkit.restapi.client.Identifier;
import org.ctoolkit.restapi.client.ResourceFacade;
import org.ctoolkit.turnonline.origin.frontend.FrontendSession;
import org.ctoolkit.turnonline.origin.frontend.identity.Role;
import org.ctoolkit.turnonline.origin.frontend.model.AuthenticatedUserPagePropsModel;
import org.ctoolkit.turnonline.origin.frontend.model.CodeBookRenderer;
import org.ctoolkit.turnonline.origin.frontend.model.CountriesModel;
import org.ctoolkit.turnonline.origin.frontend.model.LegalFormListModel;
import org.ctoolkit.turnonline.origin.frontend.model.StringCodeBookModel;
import org.ctoolkit.turnonline.origin.frontend.myaccount.event.AccountUpdateEvent;
import org.ctoolkit.turnonline.origin.frontend.myaccount.model.CompanyAddressCountryModel;
import org.ctoolkit.turnonline.origin.frontend.myaccount.model.PersonalAddressCountryModel;
import org.ctoolkit.turnonline.origin.frontend.myaccount.model.PostalAddressCountryModel;
import org.ctoolkit.turnonline.shared.feprops.MyAccountProps;
import org.ctoolkit.turnonline.shared.resource.Country;
import org.ctoolkit.turnonline.shared.resource.LegalForm;
import org.ctoolkit.turnonline.shared.resource.User;
import org.ctoolkit.turnonline.wicket.markup.html.form.ajax.IndicatingAjaxButton;
import org.ctoolkit.turnonline.wicket.markup.html.form.ajax.IndicatingAjaxDropDown;
import org.ctoolkit.turnonline.wicket.markup.html.page.DecoratedPage;
import org.ctoolkit.turnonline.wicket.model.I18NResourceModel;
import org.ctoolkit.turnonline.wicket.model.IModelFactory;
import org.ctoolkit.turnonline.wicket.myaccount.event.ToggleCompanyPersonChangeEvent;
import org.ctoolkit.turnonline.wicket.myaccount.panel.CompanyAddressPanel;
import org.ctoolkit.turnonline.wicket.myaccount.panel.CompanyBasicInfo;
import org.ctoolkit.turnonline.wicket.myaccount.panel.CompanyPersonSwitcher;
import org.ctoolkit.turnonline.wicket.myaccount.panel.PersonalAddressPanel;
import org.ctoolkit.turnonline.wicket.myaccount.panel.PersonalDataPanel;
import org.ctoolkit.turnonline.wicket.myaccount.panel.PostalAddressPanel;
import org.ctoolkit.turnonline.wicket.myaccount.panel.SimplifiedContactFieldSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Map;

/**
 * The page dedicated for basic account settings.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
@AuthorizeInstantiation( {Role.USER, Role.ACCOUNT} )
public class MyAccountBasics
        extends DecoratedPage<MyAccountProps>
{
    private static final long serialVersionUID = -1303189991396080065L;

    private static Logger logger = LoggerFactory.getLogger( MyAccountBasics.class );

    @Inject
    private ResourceFacade resources;

    @Inject
    private IModelFactory factory;

    private I18NResourceModel titleModel = new I18NResourceModel( "title.my-account" );

    public MyAccountBasics()
    {
        final AuthenticatedUserPagePropsModel model = new AuthenticatedUserPagePropsModel();
        final IModel<Map<String, Country>> countriesModel = new CountriesModel();
        final IModel<User> accountModel = new PropertyModel<>( model, "account" );

        setModel( model );

        // form
        Form<User> form = new Form<User>( "form", accountModel )
        {
            private static final long serialVersionUID = -938924956863034465L;

            @Override
            protected void onSubmit()
            {
                User account = getModelObject();
                send( getPage(), Broadcast.BREADTH, new AccountUpdateEvent( account ) );
            }
        };
        add( form );

        PropertyModel<Boolean> companyModel = new PropertyModel<>( accountModel, "company" );
        form.add( new CompanyPersonSwitcher( "isCompanyRadioGroup", companyModel ) );

        // account email fieldset
        form.add( new Label( "email", new PropertyModel<>( accountModel, "email" ) ) );

        // company basic info
        final CompanyBasicInfo<User> basicInfo = new CompanyBasicInfo<User>( "companyData", accountModel )
        {
            private static final long serialVersionUID = -2992960490517951459L;

            @Override
            protected DropDownChoice<LegalForm> provideLegalForm( String componentId )
            {
                LegalFormListModel choices = new LegalFormListModel();
                return new IndicatingAjaxDropDown<>( componentId,
                        new StringCodeBookModel<>( accountModel, "legalForm", choices ),
                        choices, new CodeBookRenderer<>() );
            }

            @Override
            protected void onConfigure()
            {
                super.onConfigure();
                User user = getModelObject();
                this.setVisible( user.isCompany() );
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
                User user = basicInfo.getModelObject();
                boolean visible = user == null || user.isVatPayer();
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
                User user = ( User ) basicInfo.getDefaultModelObject();
                String rawTaxIdValue = taxId.getRawInput();

                if ( rawTaxIdValue != null && Strings.isEmpty( user.getVatId() ) )
                {
                    // VAT state prefix proposal
                    String state = user.getState();
                    state = state == null ? "" : state.toUpperCase();
                    //noinspection unchecked
                    vatId.getModel().setObject( state + rawTaxIdValue );
                }

                // must be set manually as getDefaultProcessing() returns false
                vatPayer.setModelObject( !user.isVatPayer() );

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
        PersonalDataPanel<User> personalData = new PersonalDataPanel<User>( "personalData", accountModel )
        {
            private static final long serialVersionUID = -2808922906891760016L;

            @Override
            protected void onConfigure()
            {
                super.onConfigure();
                User user = getModelObject();
                this.setVisible( !user.isCompany() );
            }
        };
        form.add( personalData );

        // personal address panel
        PersonalAddressPanel<User> address = new PersonalAddressPanel<User>( "personalAddress", accountModel )
        {
            private static final long serialVersionUID = 3481146248010938807L;

            @Override
            protected DropDownChoice<Country> provideCountry( String componentId )
            {
                return new IndicatingAjaxDropDown<>( componentId,
                        new PersonalAddressCountryModel( accountModel, countriesModel ),
                        new CodeBookRenderer<>(),
                        countriesModel );
            }

            @Override
            protected void onConfigure()
            {
                super.onConfigure();
                User user = getModelObject();
                this.setVisible( !user.isCompany() );
            }
        };
        form.add( address );

        address.addPersonalAddressState( new OnChangeAjaxBehavior()
        {
            private static final long serialVersionUID = -1016447969591778948L;

            @Override
            protected void onUpdate( AjaxRequestTarget target )
            {
            }
        } );

        // company address panel
        CompanyAddressPanel<User> companyAddress;
        companyAddress = new CompanyAddressPanel<User>( "companyAddress", accountModel, false, false )
        {
            private static final long serialVersionUID = -6760545061622186549L;

            @Override
            protected DropDownChoice<Country> provideCountry( String componentId )
            {
                return new IndicatingAjaxDropDown<>( componentId,
                        new CompanyAddressCountryModel( accountModel, countriesModel ),
                        new CodeBookRenderer<>(),
                        countriesModel );
            }

            @Override
            protected void onConfigure()
            {
                super.onConfigure();
                User user = getModelObject();
                this.setVisible( user.isCompany() );
            }
        };
        form.add( companyAddress );

        companyAddress.addCompanyAddressState( new OnChangeAjaxBehavior()
        {
            private static final long serialVersionUID = -5476413125490349124L;

            @Override
            protected void onUpdate( AjaxRequestTarget target )
            {
            }
        } );

        PostalAddressPanel<User> postalAddress = new PostalAddressPanel<User>( "postal-address", accountModel )
        {
            private static final long serialVersionUID = -930960688138308527L;

            @Override
            protected DropDownChoice<Country> provideCountry( String componentId )
            {
                return new IndicatingAjaxDropDown<>( componentId,
                        new PostalAddressCountryModel( accountModel, countriesModel ),
                        new CodeBookRenderer<>(),
                        countriesModel );
            }
        };
        form.add( postalAddress );

        postalAddress.addPostalAddressStreet( new OnChangeAjaxBehavior()
        {
            private static final long serialVersionUID = 4050800366443676166L;

            @Override
            protected void onUpdate( AjaxRequestTarget target )
            {
            }
        } );

        form.add( new SimplifiedContactFieldSet<>( "contact", accountModel ) );
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
        else if ( event.getPayload() instanceof AccountUpdateEvent )
        {
            AccountUpdateEvent payload = ( AccountUpdateEvent ) event.getPayload();
            User account = payload.getAccount();

            if ( FrontendSession.get().isLoggedIn( account ) )
            {
                User updated = resources.update( new User( account ), new Identifier( account.getId() ) ).execute();

                FrontendSession.get().updateLoggedInUser( updated );
// FIXME        factory.updateMallCustomerFromSession( WicketUtil.getHttpServletRequest() );

                success( getString( "info.savedSuccessfully" ) );
            }
            else
            {
                logger.warn( "Something wrong happens ..." );
            }
        }

    }

    @Override
    public IModel<String> getPageTitle()
    {
        return titleModel;
    }
}
