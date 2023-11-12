package biz.turnonline.ecosystem.origin.frontend.controller;

import biz.turnonline.ecosystem.origin.frontend.model.ControllerModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

import java.util.Map;

/**
 * Internal server error
 *
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
@Controller( "/internal-server-error" )
public class InternalServerError
{
    private final Provider<ControllerModel> model;

    private final ObjectMapper mapper;

    @Inject
    public InternalServerError( Provider<ControllerModel> model, ObjectMapper mapper )
    {
        this.model = model;
        this.mapper = mapper;
    }

    @Error( global = true )
    @View( "internal-server-error" )
    @Get
    public Map<String, Object> internalServerError()
    {
        return model.get().toFreemarkerMap( mapper );
    }
}
