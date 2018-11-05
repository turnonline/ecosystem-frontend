package biz.turnonline.ecosystem.origin.frontend.content.model;

import java.io.Serializable;

/**
 * The base {@link Serializable} contract of the public content.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public interface Public
{
    /**
     * The identification of this public content. The content name identification is unique
     * until the another content with the same name has a content in different language.
     * In order to gain unique identification combine content name with locale ({@link #getContentLocale()}).
     *
     * @return the content name
     */
    String getName();

    /**
     * The title usually rendered as HTML/HEAD/TITLE.
     *
     * @return the page title
     */
    String getContentTitle();

    /**
     * The google analytics account identification.
     *
     * @return the analytics account
     */
    String getAnalyticsAccount();

    /**
     * The HTML header description meta tag.
     *
     * @return the header description
     */
    String getHeaderDescription();

    /**
     * The HTML header keywords meta tag.
     *
     * @return the header keywords
     */
    String getHeaderKeywords();

    /**
     * The optional locale of the content. The missing value means this content is in seller's default language.
     * Locale format is ISO 639 alpha-2 or alpha-3. Value is case insensitive.
     *
     * @return the locale of the content
     */
    String getContentLocale();

    /**
     * Returns an optional seller's properties.
     * A non <code>null</code> instance means this content represents a commercial content.
     *
     * @return the seller properties
     */
    Seller getSeller();
}
