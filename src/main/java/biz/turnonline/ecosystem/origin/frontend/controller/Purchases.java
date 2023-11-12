package biz.turnonline.ecosystem.origin.frontend.controller;

import biz.turnonline.ecosystem.origin.frontend.model.ControllerModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

import java.util.Map;


/**
 * Purchases page
 *
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
@Controller( "/purchases" )
public class Purchases
{
    private final Provider<ControllerModel> model;

    private final ObjectMapper mapper;

    @Inject
    public Purchases( Provider<ControllerModel> model, ObjectMapper mapper )
    {
        this.model = model;
        this.mapper = mapper;
    }

    @View( "purchases" )
    @Get
    public Map<String, Object> get()
    {
        return model.get().toFreemarkerMap( mapper );
    }
}
