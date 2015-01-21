package biz.freshcode.learn.gwt.mod1.client.experiment.dnd;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 *
 */
public interface Named extends HasId {
    Access ACCESS = GWT.create(Access.class);

    String getName();

    void setName(String s);

    interface Access<T extends Named> extends PropertyAccess<T>, HasId.Access<T> {
        ValueProvider<Named, String> name();
    }
}
