package biz.turnonline.ecosystem.origin.frontend.model;

import com.google.cloud.ServiceOptions;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;
import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.Introspected;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Firebase configuration from application.yml for 'firebase' prefix
 *
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
@Introspected
@ConfigurationProperties("firebase")
public class FirebaseConfig
        implements Serializable
{
    private static final long serialVersionUID = 649739600329865627L;

    private String signInSuccessUrl;

    private String signInUrl;

    private SignInFlow signInFlow = SignInFlow.REDIRECT;

    private String termsUrl;

    private CredentialHelper credentialHelper = CredentialHelper.NONE;

    private String apiKey;

    private String projectId = ServiceOptions.getDefaultProjectId();

    private String authDomain;

    private String databaseName;

    private String bucketName;

    private String senderId;

    private String clientId;

    private List<Provider> providers = new ArrayList<>();

    private boolean requireDisplayName;

    private ListMultimap<Provider, String> scopes;

    private ListMultimap<Provider, CustomParameter> customParameters;

    private String version = "7.16.1";

    private String uiVersion = "4.5.1";

    public FirebaseConfig()
    {
        scopes = MultimapBuilder.ListMultimapBuilder.enumKeys( Provider.class ).arrayListValues().build();
        customParameters = MultimapBuilder.ListMultimapBuilder.enumKeys( Provider.class ).arrayListValues().build();
    }

    /**
     * The URL (relative) where to redirect the user after a successful sign-in.
     * Required when the signInSuccess callback is not used or when it returns true.
     *
     * @return the where to redirect URL
     */
    public String getSignInSuccessUrl()
    {
        return signInSuccessUrl;
    }

    public void setSignInSuccessUrl( String signInSuccessUrl )
    {
        this.signInSuccessUrl = signInSuccessUrl;
    }

    /**
     * The URL (relative) where to redirect after unsuccessful sign-in
     *
     *  @return the where to redirect URL
     */
    public String getSignInUrl()
    {
        return signInUrl;
    }

    public void setSignInUrl( String signInUrl )
    {
        this.signInUrl = signInUrl;
    }

    public String getSignInFlow()
    {
        return signInFlow.getValue();
    }

    /**
     * The sign-in flow to use for IDP providers: redirect.
     * This one is the default value if not set.
     *
     * @return this configuration instance to chain fluent calls
     */
    public FirebaseConfig redirect()
    {
        this.signInFlow = SignInFlow.REDIRECT;
        return this;
    }

    /**
     * The sign-in flow to use for IDP providers:  popup.
     *
     * @return this configuration instance to chain fluent calls
     */
    public FirebaseConfig popup()
    {
        this.signInFlow = SignInFlow.POPUP;
        return this;
    }

    /**
     * The URL (relative) of the terms of service page.
     *
     * @return the relative terms path
     */
    public String getTermsUrl()
    {
        return termsUrl;
    }

    public void setTermsUrl( String termsUrl )
    {
        this.termsUrl = termsUrl;
    }

    /**
     * The credential helper configuration whether to use accountchooser.com or not.
     *
     * @return the credential helper
     */
    public String getCredentialHelper()
    {
        return credentialHelper.getValue();
    }

    /**
     * Possible values: NONE, ONE_TAP (One-tap sign-up), ACCOUNT_CHOOSER.
     *
     * @param credentialHelper the one of the possible value to be set
     */
    public void setCredentialHelper( CredentialHelper credentialHelper )
    {
        this.credentialHelper = credentialHelper;
    }

    /**
     * The {@link CredentialHelper#NONE} credential Helper to use.
     * If not set, this is the default value to be used.
     *
     * @return this configuration instance to chain fluent calls
     */
    public FirebaseConfig none()
    {
        this.credentialHelper = CredentialHelper.NONE;
        return this;
    }

    /**
     * Sets the one-tap sign-up, Google provider must be enabled in Firebase Console to be supported.
     * <p>
     * One-tap sign-up provides seamless authentication flows to your users
     * with Google's one tap sign-up and automatic sign-in APIs.
     *
     * @param clientId the client ID to be set, it can be obtained from the Credentials page of the Google APIs console,
     *                 in form 'xxx.apps.googleusercontent.com'
     * @return this configuration instance to chain fluent calls
     */
    public FirebaseConfig oneTapSignUp( @Nonnull String clientId )
    {
        String errorMessage = "Client Id is being required if Google one tap sign-up has been configured.";
        this.clientId = checkNotNull( clientId, errorMessage );
        this.credentialHelper = CredentialHelper.ONE_TAP;
        return this;
    }

    /**
     * The {@link CredentialHelper#ACCOUNT_CHOOSER} credential Helper to use.
     *
     * @return this configuration instance to chain fluent calls
     */
    public FirebaseConfig accountChooser()
    {
        this.credentialHelper = CredentialHelper.ACCOUNT_CHOOSER;
        return this;
    }

    /**
     * The Firebase API key.
     *
     * @return the api key
     */
    public String getApiKey()
    {
        return apiKey;
    }

    public void setApiKey( String apiKey )
    {
        this.apiKey = apiKey;
    }

    /**
     * The list of OAuth providers enabled for signing into your app.
     *
     * @return the list of providers
     */
    public List<String> getSignInOptions()
    {
        List<String> options = new ArrayList<>();
        for ( Provider next : providers )
        {
            options.add( next.getValue() );
        }
        return options;
    }

    public void setProviders( List<Provider> providers )
    {
        this.providers = providers;
    }

    /**
     * Adds OAuth login provider to the sign in options.
     * <p>
     * The order you specify them will be the order they are displayed
     * on the sign-in provider selection screen.
     *
     * @param provider the login provider to be added
     * @return this configuration instance to chain fluent calls
     */
    public FirebaseConfig add( @Nonnull Provider provider )
    {
        checkNotNull( provider );
        if ( providers.contains( provider ) )
        {
            throw new IllegalArgumentException( provider + " has been already configured!" );
        }

        providers.add( provider );
        return this;
    }

    /**
     * Returns the provider that has been added as last to the list.
     *
     * @return the last provider or {@code null} if list is empty
     */
    private Provider lastProvider()
    {
        if ( providers.isEmpty() )
        {
            return null;
        }

        return providers.get( providers.size() - 1 );
    }

    /**
     * Adds {@link Provider#Google} OAuth provider to the sign in options.
     * <p>
     * The order you specify them will be the order they are displayed
     * on the sign-in provider selection screen.
     *
     * @return this configuration instance to chain fluent calls
     */
    public FirebaseConfig google()
    {
        return add( Provider.Google );
    }

    /**
     * Adds {@link Provider#Facebook} OAuth provider to the sign in options.
     * <p>
     * The order you specify them will be the order they are displayed
     * on the sign-in provider selection screen.
     *
     * @return this configuration instance to chain fluent calls
     */
    public FirebaseConfig facebook()
    {
        return add( Provider.Facebook );
    }

    /**
     * Adds {@link Provider#Twitter} OAuth provider to the sign in options.
     * <p>
     * The order you specify them will be the order they are displayed
     * on the sign-in provider selection screen.
     *
     * @return this configuration instance to chain fluent calls
     */
    public FirebaseConfig twitter()
    {
        return add( Provider.Twitter );
    }

    /**
     * Adds {@link Provider#Github} OAuth provider to the sign in options.
     * <p>
     * The order you specify them will be the order they are displayed
     * on the sign-in provider selection screen.
     *
     * @return this configuration instance to chain fluent calls
     */
    public FirebaseConfig github()
    {
        return add( Provider.Github );
    }

    /**
     * Same as {@link #email(boolean)} with default value set to {@code true}.
     *
     * @return this configuration instance to chain fluent calls
     */
    public FirebaseConfig email()
    {
        return email( true );
    }

    /**
     * Adds {@link Provider#Email} OAuth provider to the sign in options.
     * <p>
     * The order you specify them will be the order they are displayed
     * on the sign-in provider selection screen.
     *
     * @param requireDisplayName true be configured to require the user to enter a display name
     * @return this configuration instance to chain fluent calls
     */
    public FirebaseConfig email( boolean requireDisplayName )
    {
        this.requireDisplayName = requireDisplayName;
        return add( Provider.Email );
    }

    /**
     * Adds given scope associated with the last added provider, for example:
     * <p>
     * {@code options.google().scope( "https://www.googleapis.com/auth/plus.login" );}
     * <p>
     * will be rendered as:
     * <pre>
     * {
     *   provider: firebase.auth.GoogleAuthProvider.PROVIDER_ID,
     *   scopes: [
     *       'https://www.googleapis.com/auth/plus.login'
     *   ]
     * }
     * </pre>
     *
     * @param scope the scope to be added to the list of 'scopes'
     * @return this configuration instance to chain fluent calls
     */
    public FirebaseConfig scope( @Nonnull String scope )
    {
        checkNotNull( scope );

        Provider provider = lastProvider();
        if ( provider == null )
        {
            throw new IllegalArgumentException( "First at least one Provider must be added." );
        }
        scopes.put( provider, scope );
        return this;
    }

    public void setClientId( String clientId )
    {
        this.clientId = clientId;
    }

    public void setRequireDisplayName( boolean requireDisplayName )
    {
        this.requireDisplayName = requireDisplayName;
    }

    /**
     * Adds custom parameter (a key value pair) associated with the last added provider, for example:
     * <p>
     * {@code options.google().parameter( "prompt", "select_account" );}
     * <p>
     * will be rendered as:
     * <pre>
     * {
     *   provider: firebase.auth.GoogleAuthProvider.PROVIDER_ID,
     *   customParameters: {
     *       prompt: 'select_account'
     *   }
     * }
     * </pre>
     *
     * @param property the property name to be added to the list of 'customParameters'
     * @param value    the property value to be added to the list of 'customParameters'
     * @return this configuration instance to chain fluent calls
     */
    public FirebaseConfig parameter( @Nonnull String property, @Nonnull String value )
    {
        checkNotNull( property );
        checkNotNull( value );

        Provider provider = lastProvider();
        if ( provider == null )
        {
            throw new IllegalArgumentException( "First at least one Provider must be added." );
        }
        customParameters.put( provider, new CustomParameter( property, value ) );
        return this;
    }

    /**
     * The list of comma separated providers to be rendered as a string.
     *
     * @return the list of comma separated providers
     */
    public String getSignInOptionsAsString()
    {
        StringBuilder builder = new StringBuilder();
        Iterator<Provider> providersIterator = providers.iterator();
        Iterator<String> scopesIterator;
        Iterator<CustomParameter> customParametersIterator;

        while ( providersIterator.hasNext() )
        {
            Provider provider = providersIterator.next();

            List<String> providerScopes = scopes.get( provider );
            List<CustomParameter> providerCustomParameters = customParameters.get( provider );

            boolean appendGoogleSpecifics = provider == Provider.Google
                    && credentialHelper == CredentialHelper.ONE_TAP;

            boolean appendEmailSpecifics = provider == Provider.Email;

            boolean hasProviderBody = appendGoogleSpecifics
                    || appendEmailSpecifics
                    || !providerScopes.isEmpty()
                    || !providerCustomParameters.isEmpty();

            // start of the provider rendering
            if ( hasProviderBody )
            {
                builder.append( "{" );
                builder.append( "\n" );
                builder.append( "provider: " );
            }

            builder.append( provider.getValue() );

            if ( appendGoogleSpecifics )
            {
                builder.append( "," );
                builder.append( "\n" );
                builder.append( "authMethod: 'https://accounts.google.com'," );
                builder.append( "\n" );
                builder.append( "clientId: '" );
                builder.append( clientId );
                builder.append( "'" );
            }
            else if ( appendEmailSpecifics )
            {
                builder.append( "," );
                builder.append( "\n" );
                builder.append( "requireDisplayName: " );
                builder.append( requireDisplayName );
            }

            // check if there are scopes declared to be rendered
            if ( !providerScopes.isEmpty() )
            {
                builder.append( "," );
                builder.append( "\n" );
                builder.append( "scopes: [" );
                builder.append( "\n" );

                scopesIterator = providerScopes.iterator();
                String next;

                while ( scopesIterator.hasNext() )
                {
                    next = scopesIterator.next();
                    builder.append( "'" );
                    builder.append( next );
                    builder.append( "'" );
                    if ( scopesIterator.hasNext() )
                    {
                        builder.append( "," );
                        builder.append( "\n" );
                    }

                }
                builder.append( "\n" );
                builder.append( "]" );
            }

            // check if there are custom parameters declared to be rendered
            if ( !providerCustomParameters.isEmpty() )
            {
                builder.append( "," );
                builder.append( "\n" );
                builder.append( "customParameters: {" );

                customParametersIterator = providerCustomParameters.iterator();
                CustomParameter next;

                while ( customParametersIterator.hasNext() )
                {
                    next = customParametersIterator.next();
                    builder.append( "\n" );
                    builder.append( next.property );
                    builder.append( ": " );
                    builder.append( "'" );
                    builder.append( next.value );
                    builder.append( "'" );
                    if ( customParametersIterator.hasNext() )
                    {
                        builder.append( "\n" );
                        builder.append( "," );
                    }

                }
                builder.append( "\n" );
                builder.append( "}" );
            }

            // end of the provider rendering
            if ( hasProviderBody )
            {
                builder.append( "\n" );
                builder.append( "}" );
            }

            if ( providersIterator.hasNext() )
            {
                builder.append( "," );
                builder.append( "\n" );
            }
        }

        return builder.toString();
    }

    /**
     * The project ID, know as App Engine application ID.
     *
     * @return the project id
     */
    public String getProjectId()
    {
        return projectId;
    }

    /**
     * Rendered as authDomain ${projectId}.firebaseapp.com and projectId ${projectId}.
     *
     * @param projectId the project ID to be set
     */
    public void setProjectId( String projectId )
    {
        this.projectId = projectId;
    }

    /**
     * The optional 'authDomain' Firebase property, that has a higher priority over
     * composition of the ${projectId}.firebaseapp.com if provided.
     *
     * @return the optional authDomain
     */
    public String getAuthDomain()
    {
        return authDomain;
    }

    /**
     * Sets the 'authDomain' Firebase property, usually as a Authorized Domain.
     *
     * @param authDomain Authorized Domain to be rendered as a 'authDomain' in Firebase init script
     */
    public void setAuthDomain( String authDomain )
    {
        this.authDomain = authDomain;
    }

    /**
     * The Firebase database name.
     *
     * @return the database name
     */
    public String getDatabaseName()
    {
        return databaseName;
    }

    /**
     * Rendered as databaseURL https://${databaseName}.firebaseio.com
     *
     * @param databaseName the database name to be set
     */
    public void setDatabaseName( String databaseName )
    {
        this.databaseName = databaseName;
    }

    /**
     * The Firebase store bucket name.
     *
     * @return the store bucket name
     */
    public String getBucketName()
    {
        return bucketName;
    }

    /**
     * Rendered as storageBucket ${bucketName}.appspot.com
     *
     * @param bucketName the bucket name to be set
     */
    public void setBucketName( String bucketName )
    {
        this.bucketName = bucketName;
    }

    /**
     * The Firebase sender Id.
     *
     * @return the sender id
     */
    public String getSenderId()
    {
        return senderId;
    }

    /**
     * Rendered as messagingSenderId ${senderId}.
     *
     * @param senderId the sender ID to be set
     */
    public void setSenderId( String senderId )
    {
        this.senderId = senderId;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion( String version )
    {
        this.version = version;
    }

    public String getUiVersion()
    {
        return uiVersion;
    }

    public void setUiVersion( String uiVersion )
    {
        this.uiVersion = uiVersion;
    }

    /**
     * The list of available sign-in providers.
     */
    enum Provider
    {
        Google( "firebase.auth.GoogleAuthProvider.PROVIDER_ID" ),
        Facebook( "firebase.auth.FacebookAuthProvider.PROVIDER_ID" ),
        Twitter( "firebase.auth.TwitterAuthProvider.PROVIDER_ID" ),
        Github( "firebase.auth.GithubAuthProvider.PROVIDER_ID" ),
        Email( "firebase.auth.EmailAuthProvider.PROVIDER_ID" );

        private String value;

        Provider( String value )
        {
            this.value = value;
        }

        public String getValue()
        {
            return value;
        }
    }

    /**
     * FirebaseUI supports the following credential helpers:
     * <ul>
     * <li>None</li>
     * <li>One-tap sign-up provides seamless authentication flows to your users
     * with Google's one tap sign-up and automatic sign-in APIs.</li>
     * <li>When accountchooser.com is enabled (enabled by default), upon signing in or signing up with email,
     * the user will be redirected to the accountchooser.com website and will be able to select
     * one of their saved accounts.</li>
     * </ul>
     */
    enum CredentialHelper
    {
        ACCOUNT_CHOOSER( "firebaseui.auth.CredentialHelper.ACCOUNT_CHOOSER_COM" ),
        ONE_TAP( "firebaseui.auth.CredentialHelper.GOOGLE_YOLO" ),
        NONE( "firebaseui.auth.CredentialHelper.NONE" );

        private String value;

        CredentialHelper( String value )
        {
            this.value = value;
        }

        public String getValue()
        {
            return value;
        }
    }

    /**
     * Two sign in flows are available:
     * <ul>
     * <li>REDIRECT, the default, will perform a full page redirect to the sign-in page of the provider.</li>
     * <li>POPUP, flow will open a popup to the sign-in page of the provider.
     * If the popup is blocked by the browser, it will fall back to a full page redirect.</li>
     * </ul>
     */
    enum SignInFlow
    {
        REDIRECT( "redirect" ),
        POPUP( "popup" );

        private String value;

        SignInFlow( String value )
        {
            this.value = value;
        }

        public String getValue()
        {
            return value;
        }
    }

    private static class CustomParameter
    {
        private String property;

        private String value;

        CustomParameter( String property, String value )
        {
            this.property = property;
            this.value = value;
        }
    }
}
