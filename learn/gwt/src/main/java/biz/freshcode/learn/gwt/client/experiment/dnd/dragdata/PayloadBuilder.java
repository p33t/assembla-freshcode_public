package biz.freshcode.learn.gwt.client.experiment.dnd.dragdata;

import java.util.Map;
import java.util.Set;

import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.newMap;
import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.newSet;

/**
 * Helper class for constructing the payload map.
 */
public class PayloadBuilder {
    public final Map<DragData.Key, Set> map = newMap();

    public <T> Set<T> getSet(Class<T> cls) {
        DragData.Key key = DragData.key(cls);
        return getSet(key);
    }

    public <T> Set<T> getSet(DragData.Key<T> key) {
        Set<T> set = map.get(key);
        if (set == null) {
            set = newSet();
            map.put(key, set);
        }
        return set;
    }

    /**
     * Convenience adder that is chainable.
     */
    public <T> PayloadBuilder add(Class<T> cls, T t) {
        getSet(cls).add(t);
        return this;
    }
}
