package biz.freshcode.learn.gwt.client.util;

import com.sencha.gxt.data.shared.ModelKeyProvider;

/**
 * General purpose identity based on object instance / mem location.
 * @param <T> The type of the object.
 */
public class IdentityHashProvider<T> implements ModelKeyProvider<T> {
    @Override
    public String getKey(T item) {
        return item.getClass().getName() + System.identityHashCode(item);
    }
}
