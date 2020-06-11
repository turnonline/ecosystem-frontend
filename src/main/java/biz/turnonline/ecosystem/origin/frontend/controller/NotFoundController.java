package biz.turnonline.ecosystem.origin.frontend.controller;

import biz.turnonline.ecosystem.origin.frontend.model.ControllerModel;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Not found page - inspired by https://guides.micronaut.io/micronaut-error-handling/guide/index.html
 *
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
@Controller("/not-found")
public class NotFoundController
{
    private Provider<ControllerModel> model;

    @Inject
    public NotFoundController( Provider<ControllerModel> model )
    {
        this.model = model;
    }

    @Error(status = HttpStatus.NOT_FOUND, global = true)
    @View( "not-found" )
    @Get
    public HttpResponse<ControllerModel> notFound()
    {
        return HttpResponse.ok( model.get() );
    }
}
