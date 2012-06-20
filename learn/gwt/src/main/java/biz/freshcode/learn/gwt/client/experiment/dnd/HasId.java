package biz.freshcode.learn.gwt.client.experiment.dnd;

import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface HasId {
    String getId();

    void setId(String s);

    interface Access<T extends HasId> extends PropertyAccess<T> {
        ModelKeyProvider<HasId> id();
    }
}
