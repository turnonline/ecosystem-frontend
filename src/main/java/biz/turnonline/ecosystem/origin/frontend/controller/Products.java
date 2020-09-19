package biz.turnonline.ecosystem.origin.frontend.controller;

import biz.turnonline.ecosystem.origin.frontend.model.ControllerModel;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Products page
 *
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
@Controller( "/products" )
public class Products
{
    private final Provider<ControllerModel> model;

    @Inject
    public Products( Provider<ControllerModel> model )
    {
        this.model = model;
    }

    @View( "products" )
    @Get
    public HttpResponse<ControllerModel> hello()
    {
        return HttpResponse.ok( model.get() );
    }
}
