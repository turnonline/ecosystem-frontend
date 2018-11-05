package biz.turnonline.ecosystem.origin.frontend.myaccount.page;

import biz.turnonline.ecosystem.origin.frontend.identity.Role;
import biz.turnonline.ecosystem.origin.frontend.model.MyAccountModel;
import biz.turnonline.ecosystem.origin.frontend.page.DecoratedPage;
import biz.turnonline.ecosystem.steward.model.Account;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.ctoolkit.wicket.standard.identity.FirebaseConfig;
import org.ctoolkit.wicket.standard.identity.behavior.FirebaseAppInit;
import org.ctoolkit.wicket.standard.model.I18NResourceModel;

import javax.inject.Inject;

/**
 * My account page
 *
 * @author <a href="mailto:pohorelec@comvai.com">Jozef Pohorelec</a>
 */
@AuthorizeInstantiation( Role.STANDARD )
public class MyAccount
        extends DecoratedPage<Account>
{
    private static final long serialVersionUID = -7738090636145150410L;

    @Inject
    private FirebaseConfig firebaseConfig;

    public MyAccount()
    {
        super( new MyAccountModel() );
        add( new FirebaseAppInit( firebaseConfig ) );

        WebMarkupContainer userDetail = new WebMarkupContainer( "user-detail" );
        userDetail.add( new FormBehavior( FormType.Horizontal ) );
        add( userDetail );

        FormGroup nameGroup = new FormGroup( "name-group", new I18NResourceModel( "label.name" ) );
        nameGroup.useFormComponentLabel( false );
        nameGroup.add( new TextField<>( "name", new PropertyModel<>( getModel(), "firstName" ) ).setEnabled( false ) );
        userDetail.add( nameGroup );

        FormGroup emailGroup = new FormGroup( "email-group", new I18NResourceModel( "label.email" ) );
        emailGroup.add( new TextField<>( "email", new PropertyModel<>( getModel(), "email" ) ).setEnabled( false ) );
        emailGroup.useFormComponentLabel( false );
        userDetail.add( emailGroup );

        FormGroup roleGroup = new FormGroup( "role-group", new I18NResourceModel( "label.role" ) );
        roleGroup.add( new TextField<>( "role", new PropertyModel<>( getModel(), "role" ) ).setEnabled( false ) );
        roleGroup.useFormComponentLabel( false );
        userDetail.add( roleGroup );
    }
}
