package biz.turnonline.ecosystem.origin.frontend.server;

import biz.turnonline.ecosystem.account.client.model.Account;
import biz.turnonline.ecosystem.origin.frontend.FrontendApplication;
import biz.turnonline.ecosystem.origin.frontend.FrontendSession;
import biz.turnonline.ecosystem.origin.frontend.identity.Role;
import biz.turnonline.ecosystem.origin.frontend.myaccount.page.AccountSettings;
import biz.turnonline.ecosystem.origin.frontend.myaccount.page.MyAccountBasics;
import biz.turnonline.ecosystem.origin.frontend.page.ShoppingCart;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.UrlResourceReference;
import org.apache.wicket.util.string.Strings;
import org.ctoolkit.wicket.turnonline.identity.page.IdentityLogin;
import org.ctoolkit.wicket.turnonline.identity.page.SignUp;
import org.ctoolkit.wicket.turnonline.menu.DefaultSchema;
import org.ctoolkit.wicket.turnonline.menu.MenuSchema;
import org.ctoolkit.wicket.turnonline.menu.SearchResponse;
import org.ctoolkit.wicket.turnonline.model.AppEngineModelFactory;
import org.ctoolkit.wicket.turnonline.theme.TurnOnlineThemeSettings;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Application specific model factory implementation.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
@Singleton
class DefaultModelFactory
        extends AppEngineModelFactory
{
    private static final AbstractReadOnlyModel<Boolean> loggedInModel = new AbstractReadOnlyModel<Boolean>()
    {
        private static final long serialVersionUID = 5964401313276368216L;

        @Override
        public Boolean getObject()
        {
            return FrontendSession.get().isLoggedIn();
        }
    };

    private static final AbstractReadOnlyModel<Long> cartItemsCountModel = new AbstractReadOnlyModel<Long>()
    {
        private static final long serialVersionUID = 3199373435214925025L;

        @Override
        public Long getObject()
        {
            return FrontendSession.get().getItemsCount();
        }
    };

    private static final AbstractReadOnlyModel<Boolean> alwaysTrueModel = new AbstractReadOnlyModel<Boolean>()
    {
        private static final long serialVersionUID = 1041959923174236623L;

        @Override
        public Boolean getObject()
        {
            return Boolean.TRUE;
        }
    };

    private static final IModel<String> defaultLogoModel = new Model<>( "/images/logo.png" );

    private static final AbstractReadOnlyModel<String> myAccountLabelModel = new AbstractReadOnlyModel<String>()
    {
        private static final long serialVersionUID = 3199373435214925025L;

        private static final int LABEL_LENGTH = 28;

        @Override
        public String getObject()
        {
            if ( loggedInModel.getObject() )
            {
                Account loggedInUser = FrontendSession.get().getLoggedInUser();
                String formattedName = loggedInUser.getEmail();
                if ( loggedInUser.getCompany() )
                {
                    if ( !Strings.isEmpty( loggedInUser.getBusinessName() ) )
                    {
                        formattedName = loggedInUser.getBusinessName();
                    }
                }
                else
                {
                    if ( !Strings.isEmpty( loggedInUser.getFirstName() ) && !Strings.isEmpty( loggedInUser.getLastName() ) )
                    {
                        formattedName = loggedInUser.getFirstName() + " " + loggedInUser.getLastName();
                    }
                }

                if ( !Strings.isEmpty( formattedName ) && formattedName.length() > LABEL_LENGTH )
                {
                    formattedName = formattedName.substring( 0, LABEL_LENGTH - 1 ) + "src/main";
                }

                return formattedName;
            }

            return null;
        }
    };

    private static String[] styles = new String[]{"grid", "layout", "form", "main"};

    public DefaultModelFactory()
    {
    }

    @Override
    public Class<? extends Page> getShoppingCartPage()
    {
        return ShoppingCart.class;
    }

    @Override
    public Class<? extends Page> getLoginPage()
    {
        return IdentityLogin.class;
    }

    @Override
    public Class<? extends Page> getSignUpPage()
    {
        return SignUp.class;
    }

    @Override
    public Class<? extends Page> getMyAccountPage()
    {
        return MyAccountBasics.class;
    }

    @Override
    public Class<? extends Page> getAccountSettingsPage()
    {
        return AccountSettings.class;
    }

    @Override
    public IModel<String> getTermsUrlModel( @Nullable IModel<?> pageModel )
    {
        return null;
    }

    /**
     * If no custom logo specified returns a default model rendering '/images/logo.png'.
     */
    @Override
    public IModel<String> getLogoUrlModel( @Nullable IModel<?> pageModel )
    {
        return defaultLogoModel;
    }

    @Override
    public IModel<Boolean> isLoggedInModel()
    {
        return loggedInModel;
    }

    @Override
    public IModel<Long> getCartItemsCountModel()
    {
        return cartItemsCountModel;
    }

    @Override
    public Roles getRoles()
    {
        return FrontendSession.get().getRoles();
    }

    @Override
    public IModel<String> getMyAccountLabelModel()
    {
        return myAccountLabelModel;
    }

    @Override
    public IModel<Boolean> getShoppingCartVisibilityModel()
    {
        return alwaysTrueModel;
    }

    @Override
    public IModel<Boolean> getSearchBoxVisibilityModel()
    {
        return alwaysTrueModel;
    }

    /**
     * In development returns not optimized css for faster development.
     */
    @Override
    public ResourceReference[] getStylesheetReference( @Nullable IModel<?> pageModel,
                                                       @Nonnull RuntimeConfigurationType type )
    {
        ResourceReference[] refs;
        Object modelObject = pageModel == null ? null : pageModel.getObject();

        if ( FrontendApplication.get().getConfigurationType() == RuntimeConfigurationType.DEVELOPMENT )
        {
            // in development use not optimized css for faster development
            refs = new ResourceReference[styles.length];
            for ( int index = 0; index < styles.length; index++ )
            {
                refs[index] = new UrlResourceReference( Url.parse( "/styles/" + styles[index] + ".css" ) );
            }
        }
        else
        {
            refs = new ResourceReference[1];
            refs[0] = TurnOnlineThemeSettings.get().getDefaultStylesheetReference();
        }
        return refs;
    }

    @Override
    public String getGoogleAnalyticsTrackingId( @Nullable IModel<?> pageModel )
    {
        return null;
    }

    @Override
    public MenuSchema provideMenuSchema( @Nonnull Page context, @Nullable Roles roles )
    {
        return new DefaultSchema( roles );
    }

    @Override
    public IModel<?> getShoppingMallModel( @Nonnull HttpServletRequest request )
    {
        return null;
    }

    @Override
    public String getAccountRole()
    {
        return Role.SELLER;
    }

    @Override
    public List<SearchResponse> getSearchResponseList( String input )
    {
        return new ArrayList<>();
    }
}
