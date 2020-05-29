package biz.turnonline.ecosystem.origin.frontend.controller;

import biz.turnonline.ecosystem.origin.frontend.model.ControllerModel;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Internal server error
 *
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
@Controller("/internal-server-error")
public class InternalServerErrorController
{
    private Provider<ControllerModel> model;

    @Inject
    public InternalServerErrorController( Provider<ControllerModel> model )
    {
        this.model = model;
    }

    @Error(global = true)
    @View( "internal-server-error" )
    @Get
    public HttpResponse<ControllerModel> internalServerError()
    {
        return HttpResponse.ok( model.get() );
    }
}
