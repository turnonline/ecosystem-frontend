package biz.turnonline.ecosystem.origin.frontend.controller;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.cookie.Cookie;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.micronaut.http.netty.cookies.NettyCookie;
import org.reactivestreams.Publisher;

import java.util.List;

/**
 * Filter is used to check language query parameter and if it is provided change locale accordingly
 * and set 'locale' cookie for further usage into http response
 *
 * @author <a href="mailto:pohorelec@turnonline.biz">Jozef Pohorelec</a>
 */
@Filter( "/**" )
public class LanguageSelectorFilter
        implements HttpServerFilter
{
    public static final String LANGUAGE_PARAM = "language";

    public static final String LANGUAGE_COOKIE = "locale";

    public static final String DEFAULT_LANGUAGE = "en";

    private static final List<String> SUPPORTED_LANGUAGES = Lists.newArrayList( "en", "sk" );

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter( HttpRequest<?> request, ServerFilterChain chain )
    {
        String language = request.getParameters().get( LANGUAGE_PARAM );
        Cookie cookie = request.getCookies()
                .findCookie( LANGUAGE_COOKIE )
                .orElse( new NettyCookie( LANGUAGE_COOKIE, DEFAULT_LANGUAGE ) );

        if ( language != null )
        {
            cookie.value( language );
        }

        String cookieValue = cookie.getValue();
        if ( !SUPPORTED_LANGUAGES.contains( Strings.isNullOrEmpty( cookieValue ) ? "" : cookieValue ) )
        {
            cookie.value( DEFAULT_LANGUAGE );
        }

        return chain.proceed( request );
    }
}
