package biz.turnonline.ecosystem.origin.frontend.controller;

import biz.turnonline.ecosystem.origin.frontend.model.ControllerModel;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Sign up page
 *
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
@Controller("/sign-in")
public class SignInController
{
    private Provider<ControllerModel> model;

    @Inject
    public SignInController( Provider<ControllerModel> model )
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
