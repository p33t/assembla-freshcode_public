package biz.freshcode.learn.gwt.client.experiment.dnd;

import com.sencha.gxt.dnd.core.client.DndDragLeaveEvent;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent;

import java.util.*;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.*;
import static java.util.Collections.unmodifiableSet;

public class DragData {
    private final String originalMessage;
    private final Map<Key, Set> payload;

    public static DragData setup(DndDragStartEvent evt, Class clsKey, Set simplePayload) {
        Map<Key, Set> m = newMap();
        m.put(key(clsKey), simplePayload);
        return setup(evt, m);
    }

    public static DragData setup(DndDragStartEvent evt, Map<Key, Set> payload) {
        String originalMessage = generateStatusMessage(payload);
        return setup(evt, payload, originalMessage);
    }

    private static DragData setup(DndDragStartEvent evt, Map<Key, Set> payload, String originalMessage) {
        DragData dd = new DragData(payload, originalMessage);
        evt.setData(dd);
        evt.getStatusProxy().update(originalMessage);
        return dd;
    }

    public static <T> DefaultKey key(Class<T> clsKey) {
        return new DefaultKey(clsKey);
    }

    public DragData(Map<Key, Set> payload, String originalMessage) {
        this.payload = normalize(payload);
        this.originalMessage = originalMessage;
    }

    public String getOriginalMessage() {
        return originalMessage;
    }

    public void restoreOriginalMessage(DndDragLeaveEvent evt) {
        evt.getStatusProxy().update(originalMessage);
    }

    /**
     * @see #hasExclusivePayload(biz.freshcode.learn.gwt.client.experiment.dnd.DragData.Key[])
     */
    public boolean hasExclusivePayload(Class... clsKeys) {
        return this.payload.size() == clsKeys.length && hasPayload(clsKeys);
    }

    /**
     * Indicates the payload contains only the given keys.  This is useful when giving feedback on what 'cannot' be done.
     */
    public boolean hasExclusivePayload(Key... keys) {
        return this.payload.size() == keys.length && hasPayload(keys);
    }

    /**
     * @see #hasExclusivePayload(biz.freshcode.learn.gwt.client.experiment.dnd.DragData.Key[])
     */
    public boolean hasExclusivePayload(Collection<Key> keys) {
        return this.payload.size() == keys.size() && hasPayload(keys);
    }

    /**
     * @see #hasPayload(biz.freshcode.learn.gwt.client.experiment.dnd.DragData.Key[])
     */
    public boolean hasPayload(Class... clsKeys) {
        List<Key> l = newList();
        for (Class c : clsKeys) l.add(key(c));
        return hasPayload(l);
    }

    /**
     * Indicates the payload contains each of the given keys.
     */
    public boolean hasPayload(Key... keys) {
        return hasPayload(Arrays.asList(keys));
    }


    /**
     * @see #hasPayload(biz.freshcode.learn.gwt.client.experiment.dnd.DragData.Key[])
     */
    public boolean hasPayload(Collection<Key> keys) {
        return this.payload.keySet().containsAll(keys);
    }

    /**
     * @see #getPayload(biz.freshcode.learn.gwt.client.experiment.dnd.DragData.Key)
     */
    public <T> Set<T> getPayload(Class<T> clsKey) {
        return getPayload(key(clsKey));
    }

    /**
     * Returns the set of items identified by the given key and is never 'null'.
     * The resulting set is read-only.
     */
    public <T> Set<T> getPayload(Key<T> key) {
        Set s = payload.get(key);
        if (s == null) return Collections.emptySet();
        return s;
    }

    /**
     * Create a copy and remove empty sets.
     */
    private Map<Key, Set> normalize(Map<Key, Set> payload) {
        Map<Key, Set> m = new HashMap<Key, Set>();
        for (Map.Entry<Key, Set> e : payload.entrySet()) {
            if (e.getValue().isEmpty()) continue;
            Set s = newSetFrom(e.getValue());
            m.put(e.getKey(), unmodifiableSet(s));
        }
        return m;
    }

    /**
     * Generate a status message for this drag data.  This will be the 'original' status message.
     */
    private static String generateStatusMessage(Map<Key, Set> payload) {
        String msg = "";
        for (Key k : payload.keySet()) {
            Set s = payload.get(k);
            if (!s.isEmpty()) {
                if (!msg.isEmpty()) msg += ",";
                msg += k.describe(s);
            }
        }
        if (msg.isEmpty()) return "<empty>";
        return msg;
    }

    /**
     * A key under which a dragged object can be stored.  Equals() and hashcode() need to be properly implemented.
     */
    public interface Key<T> {
        String describe(Set<T> s);
    }

    public static class DefaultKey<T> implements Key<T> {
        private final Class<T> cls;

        public DefaultKey(Class<T> cls) {
            this.cls = cls;
        }

        @Override
        public String describe(Set<T> s) {
            String name = cls.getName();
            // NOTE: getSimpleName does not work in GWT 2.4.0
            int ix = name.lastIndexOf('.');
            if (ix >= 0) name = name.substring(ix + 1);
            return s.size() + "x" + name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DefaultKey that = (DefaultKey) o;

            //noinspection RedundantIfStatement
            if (!cls.equals(that.cls)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return cls.hashCode();
        }
    }

    /**
     * Helper class for constructing the payload map.
     */
    public static class PayloadBuilder {
        public final Map<Key, Set> map = newMap();

        public <T> Set<T> getSet(Class<T> cls) {
            Key key = key(cls);
            return getSet(key);
        }

        public <T> Set<T> getSet(Key<T> key) {
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
}
