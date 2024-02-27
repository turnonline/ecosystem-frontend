package biz.turnonline.ecosystem.origin.frontend;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.context.ApplicationContextBuilder;
import io.micronaut.context.ApplicationContextConfigurer;
import io.micronaut.context.annotation.ContextConfigurer;
import io.micronaut.context.event.BeanCreatedEvent;
import io.micronaut.context.event.BeanCreatedEventListener;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.runtime.Micronaut;
import jakarta.inject.Singleton;

public class Application
{
    public static void main( String[] args )
    {
        Micronaut.run( Application.class );
    }

    @ContextConfigurer
    public static class DeduceCloudEnvironmentConfigurer
            implements ApplicationContextConfigurer
    {
        @Override
        public void configure( @NonNull ApplicationContextBuilder builder )
        {
            builder.deduceCloudEnvironment( true );
        }
    }

    @Singleton
    static class ObjectMapperBeanEventListener
            implements BeanCreatedEventListener<ObjectMapper>
    {
        @Override
        public ObjectMapper onCreated( BeanCreatedEvent<ObjectMapper> event )
        {
            ObjectMapper mapper = event.getBean();
            mapper.setSerializationInclusion( JsonInclude.Include.ALWAYS );
            return mapper;
        }
    }
}