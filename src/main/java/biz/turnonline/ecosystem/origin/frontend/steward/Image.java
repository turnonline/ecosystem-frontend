package biz.turnonline.ecosystem.origin.frontend.steward;

import java.io.Serializable;

/**
 * Image details.
 **/
public class Image
        implements Serializable
{
    private static final long serialVersionUID = 89414311609041583L;

    private String storageName;

    private String servingUrl;

    /**
     * The full path to the image. It's an identification in the underlying storage.
     **/
    public Image storageName( String storageName )
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
     * The full URL of the image served from the content delivery network (CDN). Provided by the service once an image ('storageName') has been uploaded.
     **/
    public Image servingUrl( String servingUrl )
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

