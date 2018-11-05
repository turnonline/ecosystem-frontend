package biz.turnonline.ecosystem.origin.frontend.content;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * The marker annotation to configure specific {@link com.google.common.eventbus.EventBus} used by subscription.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@Qualifier
@Target( {METHOD, PARAMETER, FIELD} )
@Retention( java.lang.annotation.RetentionPolicy.RUNTIME )
public @interface ContentSubscription
{
}
