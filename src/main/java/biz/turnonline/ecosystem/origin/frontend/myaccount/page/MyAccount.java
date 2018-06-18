package biz.turnonline.ecosystem.origin.frontend.myaccount.page;

import biz.turnonline.ecosystem.origin.frontend.identity.AccountProfile;
import biz.turnonline.ecosystem.origin.frontend.identity.Role;
import biz.turnonline.ecosystem.origin.frontend.model.MyAccountModel;
import biz.turnonline.ecosystem.origin.frontend.page.DecoratedPage;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.ctoolkit.wicket.standard.model.I18NResourceModel;

/**
 * My account page
 *
 * @author <a href="mailto:pohorelec@comvai.com">Jozef Pohorelec</a>
 */
@AuthorizeInstantiation( Role.USER )
public class MyAccount
        extends DecoratedPage<AccountProfile>
{
    public MyAccount()
    {
        super( new MyAccountModel() );

        WebMarkupContainer userDetail = new WebMarkupContainer( "user-detail" );
        userDetail.add( new FormBehavior( FormType.Horizontal ) );
        add( userDetail );

        FormGroup nameGroup = new FormGroup( "name-group", new I18NResourceModel( "label.name" ) );
        nameGroup.useFormComponentLabel( false );
        nameGroup.add( new TextField<>( "name", new PropertyModel<>( getModel(), "name" ) ).setEnabled( false ) );
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
