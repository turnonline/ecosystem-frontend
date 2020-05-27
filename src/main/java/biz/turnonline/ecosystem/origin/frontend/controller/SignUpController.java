package biz.turnonline.ecosystem.origin.frontend.controller;

import biz.turnonline.ecosystem.origin.frontend.model.ControllerModel;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

/**
 * Sign up page
 *
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
@Controller("/sign-up")
public class SignUpController
{
    @View( "signup" )
    @Get
    public HttpResponse<ControllerModel> hello()
    {
        return HttpResponse.ok( new ControllerModel( ) );
    }
}
