package biz.turnonline.ecosystem.origin.frontend.controller;

import biz.turnonline.ecosystem.origin.frontend.model.ControllerModel;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.netty.cookies.NettyCookie;

import java.net.URI;

import static biz.turnonline.ecosystem.origin.frontend.service.IdentityCheckSessionFilter.COOKIE_ACCOUNT_ATTRIBUTE;

/**
 * Logout page
 *
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
@Controller( "/logout" )
public class LogoutController
{
    private String signUpPath = "/sign-up";

    @Get
    public HttpResponse<ControllerModel> get( )
    {
        MutableHttpResponse<ControllerModel> response = HttpResponse.redirect( URI.create( signUpPath ) );

        NettyCookie accountCookie = new NettyCookie( COOKIE_ACCOUNT_ATTRIBUTE, "" );
        accountCookie.maxAge( 0 );
        accountCookie.path( "/" );
        response.cookie( accountCookie );

        return response;
    }
}
