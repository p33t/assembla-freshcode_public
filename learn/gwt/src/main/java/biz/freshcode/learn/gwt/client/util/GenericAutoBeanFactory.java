package biz.freshcode.learn.gwt.client.util;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * An autobean factory that only produces a single type of autobean.
 * NOTE: Extending GenericAutoBeanFactory breaks AutoBeanFactorySource (and any unit testing)
 * unless the 'auto' method is redeclared.
 */
public interface GenericAutoBeanFactory<T> extends AutoBeanFactory {
    AutoBean<T> auto();
}
