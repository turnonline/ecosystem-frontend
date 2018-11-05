package biz.turnonline.ecosystem.origin.frontend.content.model;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * The common content contract.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class CommonContent
        extends PublicContent
{
    private static final long serialVersionUID = -8094524834789535930L;

    private List<Gate> gateways;

    private Boolean showGmailLogin;

    private Boolean showFacebookLogin;

    private List<String> loginProviders;

    public CommonContent()
    {
    }

    public CommonContent( String name )
    {
        super( name );
    }

    /**
     * Returns the list of supported payment methods that customer can use to pay.
     *
     * @return the list of supported payment methods
     */
    public List<Gate> getGateways()
    {
        return MoreObjects.firstNonNull( gateways, ImmutableList.<Gate>of() );
    }

    /**
     * Property setter.
     *
     * @param gateways value to be set
     */
    public void setGateways( List<Gate> gateways )
    {
        this.gateways = gateways;
    }

    public Boolean isShowGmailLogin()
    {
        return showGmailLogin;
    }

    public void setShowGmailLogin( Boolean showGmailLogin )
    {
        this.showGmailLogin = showGmailLogin;
    }

    public Boolean isShowFacebookLogin()
    {
        return showFacebookLogin;
    }

    public void setShowFacebookLogin( Boolean showFacebookLogin )
    {
        this.showFacebookLogin = showFacebookLogin;
    }

    /**
     * Returns the list of supported third-party login providers that customer can use to log in.
     *
     * @return the list of supported login providers
     */
    public List<String> getLoginProviders()
    {
        return MoreObjects.firstNonNull( loginProviders, ImmutableList.<String>of() );
    }

    /**
     * Property setter.
     *
     * @param loginProviders value to be set
     */
    public void setLoginProviders( List<String> loginProviders )
    {
        this.loginProviders = loginProviders;
    }
}
