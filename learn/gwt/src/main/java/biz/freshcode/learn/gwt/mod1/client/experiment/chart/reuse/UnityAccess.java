package biz.freshcode.learn.gwt.mod1.client.experiment.chart.reuse;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;

/**
 * Adapter for accessing a the same integer value.
 */
public class UnityAccess<T> implements ValueProvider<T, T>, ModelKeyProvider<T> {
    public static <T> UnityAccess<T> unityAccess() {
        return new UnityAccess<T>();
    }

    @Override
    public T getValue(T i) {
        return i;
    }

    @Override
    public void setValue(T object, T value) {
        // nothing
    }

    @Override
    public String getPath() {
        return "x";
    }

    @Override
    public String getKey(T item) {
        return item.toString();
    }
}
