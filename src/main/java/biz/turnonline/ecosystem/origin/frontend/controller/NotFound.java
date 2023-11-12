package biz.turnonline.ecosystem.origin.frontend.controller;

import biz.turnonline.ecosystem.origin.frontend.model.ControllerModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

import java.util.Map;

/**
 * Not found page - inspired by https://guides.micronaut.io/micronaut-error-handling/guide/index.html
 *
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
@Controller( "/not-found" )
public class NotFound
{
    private final Provider<ControllerModel> model;

    private final ObjectMapper mapper;

    @Inject
    public NotFound( Provider<ControllerModel> model, ObjectMapper mapper )
    {
        this.model = model;
        this.mapper = mapper;
    }

    @Error( status = HttpStatus.NOT_FOUND, global = true )
    @View( "not-found" )
    @Get
    public Map<String, Object> notFound()
    {
        return model.get().toFreemarkerMap( mapper );
    }
}
