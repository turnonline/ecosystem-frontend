package biz.turnonline.ecosystem.origin.frontend.controller;

import biz.turnonline.ecosystem.origin.frontend.model.ControllerModel;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

/**
 * Purchases page
 *
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
@Controller("/purchases")
public class PurchasesController
{
    @View( "purchases" )
    @Get
    public HttpResponse<ControllerModel> get()
    {
        return HttpResponse.ok( new ControllerModel( ) );
    }
}
