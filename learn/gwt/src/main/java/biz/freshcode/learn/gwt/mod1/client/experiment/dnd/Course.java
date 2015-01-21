package biz.freshcode.learn.gwt.mod1.client.experiment.dnd;

import biz.freshcode.learn.gwt.mod1.client.util.GenericAutoBeanFactory;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 *
 */
public interface Course extends Named, Attended<Student> {
    Factory FACTORY = GWT.create(Factory.class);
    Access ACCESS = GWT.create(Access.class);


    interface Factory extends GenericAutoBeanFactory<Course> {
    }

    interface Access extends PropertyAccess<Course>, Named.Access<Course> {

    }
}
