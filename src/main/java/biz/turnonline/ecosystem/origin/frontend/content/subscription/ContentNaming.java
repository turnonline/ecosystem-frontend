package biz.turnonline.ecosystem.origin.frontend.content.subscription;

import biz.turnonline.ecosystem.origin.frontend.content.ContentSubscription;
import biz.turnonline.ecosystem.origin.frontend.content.model.CommonContent;
import biz.turnonline.ecosystem.origin.frontend.content.model.EventContent;
import biz.turnonline.ecosystem.origin.frontend.content.model.MallContent;
import biz.turnonline.ecosystem.origin.frontend.content.model.PayInvoiceContent;
import biz.turnonline.ecosystem.origin.frontend.content.model.ProductContent;
import biz.turnonline.ecosystem.origin.frontend.content.model.TermsContent;
import org.ctoolkit.restapi.client.Identifier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Locale;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The content naming convenient methods.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class ContentNaming
        implements Serializable
{
    private static final long serialVersionUID = 2810741752930636274L;

    private final Locale defaultLocale;

    @Inject
    public ContentNaming( @ContentSubscription Locale defaultLocale )
    {
        this.defaultLocale = defaultLocale;
    }

    private String idName( @Nonnull Class pageClass,
                           @Nonnull String domain,
                           @Nullable String id )
    {
        checkNotNull( pageClass );
        checkNotNull( domain );

        String key = null;

        if ( pageClass == CommonContent.class )
        {
            key = "_*@" + domain;
        }
        else if ( ProductContent.class == pageClass )
        {
            key = id + "@" + domain;
        }
        else if ( MallContent.class == pageClass )
        {
            key = "_mall@" + domain;
        }
        else if ( EventContent.class == pageClass )
        {
            key = id + "@" + domain;
        }
        else if ( TermsContent.class == pageClass )
        {
            key = "_terms@" + domain;
        }
        else if ( PayInvoiceContent.class == pageClass )
        {
            checkNotNull( id, "For " + PayInvoiceContent.class.getSimpleName() + " ID/key must be provided" );
            key = "_payinvoice." + id + "@" + domain;
        }

        return key == null ? null : key.toLowerCase();
    }

    public String idName( @Nonnull Class pageClass, @Nonnull String domain )
    {
        return idName( pageClass, domain, null );
    }

    /**
     * Prefix the content name with locale, either with given or with default one.
     *
     * @param contentName the content name
     * @param locale      the user provided locale
     * @return the prefixed content name
     */
    public String prefixName( @Nonnull String contentName, @Nullable String locale )
    {
        String finalLocale;
        if ( locale == null )
        {
            finalLocale = defaultLocale.getLanguage();
        }
        else
        {
            finalLocale = locale;
        }

        return finalLocale + "::" + contentName;
    }

    /**
     * Compose the full content name always prefixed with locale, either with given or with default one.
     *
     * @param pageClass  the target content class
     * @param identifier the unique identifier of the resource
     * @param locale     the optional language the client has configured to prefer in results if applicable
     * @return the content name
     */
    public String composeFullName( @Nonnull Class pageClass,
                                   @Nonnull Identifier identifier,
                                   @Nullable Locale locale )
    {
        String contentName;
        if ( identifier.hasChild() )
        {
            contentName = idName( pageClass, identifier.getString(), identifier.child().getString() );
        }
        else
        {
            contentName = idName( pageClass, identifier.getString() );
        }
        return prefixName( contentName, locale == null ? null : locale.getLanguage() );
    }
}
