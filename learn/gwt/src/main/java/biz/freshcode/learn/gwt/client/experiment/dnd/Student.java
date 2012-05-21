package biz.freshcode.learn.gwt.client.experiment.dnd;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 *
 */
public interface Student extends Named, IdAble {
    Factory FACTORY = GWT.create(Factory.class);
    Access ACCESS = GWT.create(Access.class);

    interface Factory extends AutoBeanFactory {
        AutoBean<Student> auto();
    }

     interface Access extends PropertyAccess<Student>, Named.Access<Student> {

    }
}
