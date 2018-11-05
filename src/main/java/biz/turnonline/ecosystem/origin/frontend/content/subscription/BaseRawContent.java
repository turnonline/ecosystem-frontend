package biz.turnonline.ecosystem.origin.frontend.content.subscription;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.services.pubsub.model.PubsubMessage;
import com.google.common.base.Splitter;
import com.googlecode.objectify.annotation.Id;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * There is a limit on the unindexed content, data cannot exceed size 1,048,487 bytes.
 * https://cloud.google.com/datastore/docs/concepts/limits
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
abstract class BaseRawContent<T>
        implements Serializable
{
    private static final long serialVersionUID = -4455679222818613582L;

    private final Class<T> type;

    @Id
    private String name;

    private String content;

    /**
     * Constructor.
     *
     * @param type the target type of the serialized content
     */
    BaseRawContent( Class<T> type )
    {
        this.type = type;
    }

    /**
     * Constructor.
     *
     * @param name the unique name of the content
     * @param type the target type of the serialized content
     */
    BaseRawContent( String name, Class<T> type )
    {
        this.name = checkNotNull( name, "Content's unique name must be provided." );
        this.type = checkNotNull( type, "Content's type must be provided." );
    }

    /**
     * Deserialize JSON content from data (either encoded in Base64 form or regular string)
     * and returns the target instance populated with values from the JSON data.
     *
     * @param mapper the JSON mapper
     * @return the concrete instance populated with values from the JSON data
     * @throws IOException if deserialization fails
     */
    public T convert( ObjectMapper mapper ) throws IOException
    {
        if ( content == null )
        {
            return null;
        }

        String decoded;
        if ( Base64.isBase64( content.getBytes() ) )
        {
            decoded = new String( new PubsubMessage().setData( content ).decodeData(), Charset.forName( "UTF-8" ) );
        }
        else
        {
            decoded = content;
        }
        return mapper.readValue( decoded, type );
    }

    public String getName()
    {
        return name;
    }

    /**
     * Sets the content.
     * The string is allowed to be either encoded in Base64 form or regular string.
     *
     * @param content the content to be set
     */
    public void setContent( String content )
    {
        this.content = checkNotNull( content );
    }

    /**
     * Returns the length of the content.
     *
     * @return the length of the content
     */
    public int length()
    {
        if ( content == null )
        {
            return 0;
        }
        return content.length();
    }

    /**
     * Extracts the owner of the content in form of the full domain taken from the content name.
     *
     * @return the domain as owner of the content
     */
    public String getOwnerFromName()
    {
        checkNotNull( this.name, "Content name cannot be null" );
        List<String> strings = Splitter.on( "@" ).omitEmptyStrings().splitToList( this.name );
        return strings.isEmpty() ? null : strings.get( strings.size() - 1 );
    }

    /**
     * Extracts the locale taken from the content name.
     * It's expected content name will always start with locale prefix (received or default).
     *
     * @return the locale
     */
    public Locale getLocaleFromName()
    {
        checkNotNull( this.name, "Content name cannot be null" );
        List<String> strings = Splitter.on( "::" ).omitEmptyStrings().splitToList( this.name );

        Locale locale;
        if ( strings.size() >= 2 )
        {
            // only single locale separator found
            locale = new Locale( strings.get( 0 ) );
        }
        else
        {
            // there is no locale separator, this should not happen with valid full content name
            locale = null;
        }

        return locale;
    }
}
