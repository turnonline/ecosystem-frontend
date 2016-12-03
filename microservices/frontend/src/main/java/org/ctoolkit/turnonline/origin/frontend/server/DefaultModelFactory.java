package org.ctoolkit.turnonline.origin.frontend.server;

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
import org.ctoolkit.turnonline.origin.frontend.FrontendApplication;
import org.ctoolkit.turnonline.origin.frontend.FrontendSession;
import org.ctoolkit.turnonline.origin.frontend.identity.AuthenticatedUser;
import org.ctoolkit.turnonline.origin.frontend.myaccount.page.AccountSettings;
import org.ctoolkit.turnonline.origin.frontend.myaccount.page.MyAccountBasics;
import org.ctoolkit.turnonline.origin.frontend.page.ShoppingCart;
import org.ctoolkit.turnonline.shared.feprops.IPageProperties;
import org.ctoolkit.turnonline.shared.feprops.PageProperties;
import org.ctoolkit.turnonline.shared.resource.User;
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
import java.util.Locale;

import static org.apache.wicket.ThreadContext.getSession;

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

    private static final AbstractReadOnlyModel<Roles> rolesModel = new AbstractReadOnlyModel<Roles>()
    {
        private static final long serialVersionUID = -3967574798731825510L;

        @Override
        public Roles getObject()
        {
            return FrontendSession.get().getRoles();
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
                AuthenticatedUser loggedInUser = FrontendSession.get().getLoggedInUser();
                String formattedName = loggedInUser.getEmail();
                if ( loggedInUser.isCompany() )
                {
                    if ( !Strings.isEmpty( loggedInUser.getBusinessName() ) )
                    {
                        formattedName = loggedInUser.getBusinessName();
                    }
                }
                else
                {
                    if ( !Strings.isEmpty( loggedInUser.getName() ) && !Strings.isEmpty( loggedInUser.getSurname() ) )
                    {
                        formattedName = loggedInUser.getName() + " " + loggedInUser.getSurname();
                    }
                }

                if ( !Strings.isEmpty( formattedName ) && formattedName.length() > LABEL_LENGTH )
                {
                    formattedName = formattedName.substring( 0, LABEL_LENGTH - 1 ) + "..";
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
        String termsUrl = null;
        Object modelObject = pageModel == null ? null : pageModel.getObject();

        if ( modelObject instanceof PageProperties )
        {
            PageProperties properties = ( PageProperties ) modelObject;
            termsUrl = properties.getTermsUrl();
        }
//        TODO finish getTermsUrl for shopping mall
//        else if ( modelObject instanceof ShoppingMall )
//        {
//            ShoppingMall mall = ( ShoppingMall ) modelObject;
//            termsUrl = mall.getTermsUrl();
//        }

        return termsUrl == null ? null : new Model<>( termsUrl );
    }

    /**
     * If no custom logo specified returns a default model rendering '/images/logo.png'.
     */
    @Override
    public IModel<String> getLogoUrlModel( @Nullable IModel<?> pageModel )
    {
        IModel<String> logoModel = null;
        Object modelObject = pageModel == null ? null : pageModel.getObject();

        if ( modelObject instanceof IPageProperties
                && !Strings.isEmpty( ( ( IPageProperties ) modelObject ).getSellerLogoUrl() ) )
        {
            IPageProperties properties = ( IPageProperties ) modelObject;
            logoModel = new Model<>( properties.getSellerLogoUrl() );
        }

        if ( logoModel == null )
        {
            logoModel = defaultLogoModel;
        }

        return logoModel;
    }

    @Override
    public IModel<Locale> getSessionLocaleModel( @Nullable IModel<?> pageModel )
    {
        IModel<Locale> model = null;
        Object modelObject = pageModel == null ? null : pageModel.getObject();

        if ( modelObject instanceof IPageProperties )
        {
            IPageProperties props = ( IPageProperties ) modelObject;
            if ( !Strings.isEmpty( props.getSellerLocale() ) )
            {
                Locale locale = new Locale( props.getSellerLocale() );
                model = new Model<>( locale );
            }
        }
        return model;
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
    public IModel<Roles> getRolesModel()
    {
        return rolesModel;
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

            if ( modelObject instanceof IPageProperties
                    && ( ( IPageProperties ) modelObject ).isCustomStyling() )
            {
                String style = getSession().getStyle();
                String themeName = TurnOnlineThemeSettings.STANDARD + "-" + style;
                refs[0] = TurnOnlineThemeSettings.get().getStylesheetReference( themeName );
            }
            else
            {
                refs[0] = TurnOnlineThemeSettings.get().getDefaultStylesheetReference();
            }
        }
        return refs;
    }

    @Override
    public String getGoogleAnalyticsTrackingId( @Nullable IModel<?> pageModel )
    {
        String trackingId = null;
        Object modelObject = pageModel == null ? null : pageModel.getObject();

        if ( modelObject instanceof IPageProperties )
        {
            IPageProperties properties = ( IPageProperties ) modelObject;
            trackingId = properties.getAnalyticsAccount();
        }

        return trackingId;
    }

    @Override
    public MenuSchema provideMenuSchema( @Nonnull Page context, @Nonnull IModel<Roles> roles )
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
        return User.Role.ACCOUNT.name();
    }

    @Override
    public List<SearchResponse> getSearchResponseList( String input )
    {
        return new ArrayList<>();
    }
}
