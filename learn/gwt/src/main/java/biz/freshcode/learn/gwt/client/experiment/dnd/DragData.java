package biz.freshcode.learn.gwt.client.experiment.dnd;

import com.sencha.gxt.dnd.core.client.DndDragLeaveEvent;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent;

import java.util.*;

public class DragData<T> {
    private String statusMessage;
    protected final Map<Key, Set> payload = new HashMap<Key, Set>();

    public void finishSetup(DndDragStartEvent evt) {
        statusMessage = generateStatusMessage();
        evt.setData(this);
        evt.getStatusProxy().update(statusMessage);
    }

    public void restoreOriginalMessage(DndDragLeaveEvent evt) {
        evt.getStatusProxy().update(statusMessage);
    }

    public <T> void addPayload(Key<T> key, T t) {
        getPayload(key).add(t);
    }

    public <T> void simplePayload(Class<T> clsKey, Collection<T> coll) {
        getPayload(new DefaultKey(clsKey)).addAll(coll);
    }

    public <T> Set<T> getPayload(Class<T> clsKey) {
        return getPayload(new DefaultKey(clsKey));
    }

    /**
     * Returns the set of items identified by the given key.  This method dynamically creates a set and NEVER returns 'null'.
     */
    public <T> Set<T> getPayload(Key<T> key) {
        Set<T> set = payload.get(key);
        if (set == null) {
            set = new HashSet();
            payload.put(key, set);
        }
        return set;
    }

    /**
     * Generate a status message for this drag data.  This will be the 'original' status message.
     */
    protected String generateStatusMessage() {
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

            if (!cls.equals(that.cls)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return cls.hashCode();
        }
    }
}
