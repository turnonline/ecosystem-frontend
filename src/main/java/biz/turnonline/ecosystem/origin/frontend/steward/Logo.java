package biz.turnonline.ecosystem.origin.frontend.steward;

import java.io.Serializable;

/**
 * Logo image details.
 **/
public class Logo
        implements Serializable
{
    private static final long serialVersionUID = -4932478071251493988L;

    private String storageName;

    private String servingUrl;

    /**
     * The full path to the logo. It's an identification in the underlying storage.
     **/
    public Logo storageName( String storageName )
    {
        this.storageName = storageName;
        return this;
    }

    public String getStorageName()
    {
        return storageName;
    }

    public void setStorageName( String storageName )
    {
        this.storageName = storageName;
    }

    /**
     * The full URL of the logo served from the content delivery network (CDN). Provided by the service once an image ('storageName') has been uploaded.
     **/
    public Logo servingUrl( String servingUrl )
    {
        this.servingUrl = servingUrl;
        return this;
    }

    public String getServingUrl()
    {
        return servingUrl;
    }

    public void setServingUrl( String servingUrl )
    {
        this.servingUrl = servingUrl;
    }
}

