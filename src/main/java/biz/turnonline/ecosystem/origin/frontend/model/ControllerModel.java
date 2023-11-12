package biz.turnonline.ecosystem.origin.frontend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.context.ServerRequestContext;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import static biz.turnonline.ecosystem.origin.frontend.controller.LanguageSelectorFilter.DEFAULT_LANGUAGE;
import static biz.turnonline.ecosystem.origin.frontend.controller.LanguageSelectorFilter.LANGUAGE_COOKIE;

/**
 * @author <a href="mailto:pohorelec@turnonline.biz">Jozef Pohorelec</a>
 */
@Introspected
public class ControllerModel
{
    private String locale;

    private FirebaseConfig firebaseConfig;

    private GwtConfig gwtConfig;

    public String getLocale()
    {
        return locale;
    }

    public void setLocale( String locale )
    {
        this.locale = locale;
    }

    public FirebaseConfig getFirebaseConfig()
    {
        return firebaseConfig;
    }

    public void setFirebaseConfig( FirebaseConfig firebaseConfig )
    {
        this.firebaseConfig = firebaseConfig;
    }

    public GwtConfig getGwtConfig()
    {
        return gwtConfig;
    }

    public void setGwtConfig( GwtConfig gwtConfig )
    {
        this.gwtConfig = gwtConfig;
    }

    /**
     * Returns locale sensitive messages, including
     * <ul>
     *     <li>sets this {@link #setLocale(String)} with actual value</li>
     * </ul>
     * and .
     */
    @JsonIgnore
    public ResourceBundle getResourceBundle()
    {
        AtomicReference<String> locale = new AtomicReference<>( DEFAULT_LANGUAGE );

        ServerRequestContext.currentRequest()
                .flatMap( request -> request.getCookies().findCookie( LANGUAGE_COOKIE ) )
                .ifPresent( cookie -> {
                    String value = cookie.getValue();
                    if ( !Strings.isNullOrEmpty( value ) )
                    {
                        locale.set( value );
                    }
                } );

        setLocale( locale.get() );

        return ResourceBundle.getBundle( "messages", new Locale( locale.get() ) );
    }

    @JsonIgnore
    public Map<String, Object> toFreemarkerMap( ObjectMapper mapper )
    {
        return toFreemarkerMap( "", mapper );
    }

    @JsonIgnore
    public Map<String, Object> toFreemarkerMap( String home, ObjectMapper mapper )
    {
        ResourceBundle resourceBundle = this.getResourceBundle();
        Map<String, Object> map = mapper.convertValue( this, new TypeReference<>()
        {
        } );
        map.put( "messages", resourceBundle );
        return map;
    }
}
