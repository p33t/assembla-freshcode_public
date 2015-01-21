package biz.freshcode.learn.gwt.mod1.client.experiment.dnd;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * A level of indirection... because its handy.
 */
public interface Ref<T> {
    Factory FACTORY = GWT.create(Factory.class);

    void setObj(T t);

    T getObj();


    interface Factory extends AutoBeanFactory {
        <T> AutoBean<Ref<T>> auto();
    }
}
