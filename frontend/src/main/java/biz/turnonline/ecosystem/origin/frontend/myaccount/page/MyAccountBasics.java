package biz.turnonline.ecosystem.origin.frontend.myaccount.page;

import biz.turnonline.ecosystem.origin.frontend.identity.AccountProfile;
import biz.turnonline.ecosystem.origin.frontend.identity.Role;
import biz.turnonline.ecosystem.origin.frontend.model.MyAccountModel;
import biz.turnonline.ecosystem.origin.frontend.myaccount.event.AccountUpdateEvent;
import org.apache.wicket.Component;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.ctoolkit.wicket.standard.identity.FirebaseConfig;
import org.ctoolkit.wicket.standard.identity.behavior.FirebaseAppInit;
import org.ctoolkit.wicket.standard.markup.html.form.ajax.IndicatingAjaxButton;
import org.ctoolkit.wicket.standard.model.I18NResourceModel;
import org.ctoolkit.wicket.turnonline.markup.html.page.DecoratedPage;
import org.ctoolkit.wicket.turnonline.myaccount.event.ToggleCompanyPersonChangeEvent;
import org.ctoolkit.wicket.turnonline.myaccount.panel.PersonalDataPanel;

import javax.inject.Inject;

/**
 * The page dedicated for basic account settings.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@AuthorizeInstantiation( {Role.STANDARD} )
public class MyAccountBasics
        extends DecoratedPage<AccountProfile>
{
    private static final long serialVersionUID = -1303189991396080065L;

    private I18NResourceModel titleModel = new I18NResourceModel( "title.my-account" );

    @Inject
    private FirebaseConfig firebaseConfig;

    public MyAccountBasics()
    {
        add( new FirebaseAppInit( firebaseConfig ) );

        final MyAccountModel accountModel = new MyAccountModel();
        setModel( accountModel );

        // form
        Form<AccountProfile> form = new Form<AccountProfile>( "form", accountModel )
        {
            private static final long serialVersionUID = -938924956863034465L;

            @Override
            protected void onSubmit()
            {
                AccountProfile account = getModelObject();
                send( getPage(), Broadcast.BREADTH, new AccountUpdateEvent( account ) );
            }
        };
        add( form );

        // account email fieldset
        form.add( new Label( "email", new PropertyModel<>( accountModel, "email" ) ) );

        // personal data panel
        PersonalDataPanel<AccountProfile> personalData = new PersonalDataPanel<>( "personalData", accountModel );
        form.add( personalData );

        // save button
        form.add( new IndicatingAjaxButton( "save", form ) );
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
