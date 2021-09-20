package biz.turnonline.ecosystem.origin.frontend.controller;

import biz.turnonline.ecosystem.origin.frontend.model.ControllerModel;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

/**
 * Sign up page
 *
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
@Controller( "/sign-in" )
public class SignIn
{
    private final Provider<ControllerModel> model;

    @Inject
    public SignIn( Provider<ControllerModel> model )
    {
        this.model = model;
    }

    @View( "sign-in" )
    @Get
    public HttpResponse<ControllerModel> hello()
    {
        return HttpResponse.ok( model.get() );
    }
}
