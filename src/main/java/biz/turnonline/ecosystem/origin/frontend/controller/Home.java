package biz.turnonline.ecosystem.origin.frontend.controller;

import biz.turnonline.ecosystem.origin.frontend.model.ControllerModel;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

/**
 * Home page
 *
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
@Controller
public class Home
{
    // https://fezvrasta.github.io/bootstrap-material-design/docs/4.0/bootstrap-components/navbar/

    private final Provider<ControllerModel> model;

    @Inject
    public Home( Provider<ControllerModel> model )
    {
        this.model = model;
    }

    @View( "home" )
    @Get
    public HttpResponse<ControllerModel> get()
    {
        return HttpResponse.ok( model.get() );
    }
}
