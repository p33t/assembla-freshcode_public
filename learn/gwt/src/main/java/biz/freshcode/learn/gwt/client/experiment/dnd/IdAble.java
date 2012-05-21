package biz.freshcode.learn.gwt.client.experiment.dnd;

import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 *
 */
public interface IdAble {
    String getId();

    void setId(String s);

    interface Access<T  extends IdAble> extends PropertyAccess<T> {
        ModelKeyProvider<IdAble> id();
    }

}
