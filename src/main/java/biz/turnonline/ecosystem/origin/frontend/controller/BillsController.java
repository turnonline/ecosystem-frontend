package biz.turnonline.ecosystem.origin.frontend.controller;

import biz.turnonline.ecosystem.origin.frontend.model.ControllerModel;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Bills page
 *
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
@Controller("/bills")
public class BillsController
{
    private Provider<ControllerModel> model;

    @Inject
    public BillsController( Provider<ControllerModel> model )
    {
        this.model = model;
    }

    @View( "bills" )
    @Get
    public HttpResponse<ControllerModel> get()
    {
        return HttpResponse.ok( model.get() );
    }
}
