package biz.turnonline.ecosystem.origin.frontend.controller;

import biz.turnonline.ecosystem.origin.frontend.model.ControllerModel;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Billing page
 *
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
@Controller( "/billing" )
public class Billing
{
    private Provider<ControllerModel> model;

    @Inject
    public Billing( Provider<ControllerModel> model )
    {
        this.model = model;
    }

    @View( "billing" )
    @Get
    public HttpResponse<ControllerModel> get()
    {
        return HttpResponse.ok( model.get() );
    }
}
