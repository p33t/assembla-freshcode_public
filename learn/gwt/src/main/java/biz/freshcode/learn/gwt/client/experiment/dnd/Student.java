package biz.freshcode.learn.gwt.client.experiment.dnd;

import biz.freshcode.learn.gwt.client.util.GenericAutoBeanFactory;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 *
 */
public interface Student extends Named, IdAble {
    Factory FACTORY = GWT.create(Factory.class);
    Access ACCESS = GWT.create(Access.class);

    interface Factory extends GenericAutoBeanFactory<Student> {
    }

     interface Access extends PropertyAccess<Student>, Named.Access<Student> {

    }
}
