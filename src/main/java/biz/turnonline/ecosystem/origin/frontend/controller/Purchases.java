package biz.turnonline.ecosystem.origin.frontend.controller;

import biz.turnonline.ecosystem.origin.frontend.model.ControllerModel;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;
import jakarta.inject.Inject;
import jakarta.inject.Provider;


/**
 * Purchases page
 *
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
@Controller( "/purchases" )
public class Purchases
{
    private final Provider<ControllerModel> model;

    @Inject
    public Purchases( Provider<ControllerModel> model )
    {
        this.model = model;
    }

    @View( "purchases" )
    @Get
    public HttpResponse<ControllerModel> get()
    {
        return HttpResponse.ok( model.get() );
    }
}
