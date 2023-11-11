package biz.turnonline.ecosystem.origin.frontend;

import io.micronaut.context.ApplicationContextBuilder;
import io.micronaut.context.ApplicationContextConfigurer;
import io.micronaut.context.annotation.ContextConfigurer;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.runtime.Micronaut;

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
}