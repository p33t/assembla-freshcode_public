package biz.freshcode.learn.gwt.client.uispike;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * A marker annotation that enables locating of bean builders (potentially for regeneration)
 */
@Retention(RetentionPolicy.SOURCE)
public @interface BeanBuilder {
    Class value();
}
