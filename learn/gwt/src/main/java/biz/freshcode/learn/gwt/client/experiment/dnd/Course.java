package biz.freshcode.learn.gwt.client.experiment.dnd;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 *
 */
public interface Course extends Named, Attended<Student> {
    Factory FACTORY = GWT.create(Factory.class);
    Access ACCESS = GWT.create(Access.class);


    interface Factory extends AutoBeanFactory {
        AutoBean<Course> auto();
    }

    interface Access extends PropertyAccess<Course>, Named.Access<Course> {

    }
}
