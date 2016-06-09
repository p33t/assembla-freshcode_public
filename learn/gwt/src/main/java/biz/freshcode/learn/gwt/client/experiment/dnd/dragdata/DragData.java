package biz.freshcode.learn.gwt.client.experiment.dnd.dragdata;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.sencha.gxt.dnd.core.client.DndDragLeaveEvent;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent;

import java.util.*;

import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.*;
import static java.util.Collections.unmodifiableSet;

/**
 * A rich data type for drag and drop.  It stores sets of objects in a map.  It also facilitates restoration of
 * the original status message which GXT does not do natively.
 */
public class DragData {
    private final SafeHtml originalMessage;
    private final Map<Key, Set> payload;

    public static DragData setup(DndDragStartEvent evt, Class clsKey, Set simplePayload) {
        Map<Key, Set> m = newMap();
        m.put(key(clsKey), simplePayload);
        return setup(evt, m);
    }

    public static DragData setup(DndDragStartEvent evt, Map<Key, Set> payload) {
        SafeHtml originalMessage = generateStatusMessage(payload);
        return setup(evt, payload, originalMessage);
    }

    private static DragData setup(DndDragStartEvent evt, Map<Key, Set> payload, SafeHtml originalMessage) {
        DragData dd = new DragData(payload, originalMessage);
        evt.setData(dd);
        evt.getStatusProxy().update(originalMessage);
        return dd;
    }

    /**
     * Returns a commonly used "Class" based implementation of a Key.
     */
    public static <T> Key key(Class<T> clsKey) {
        return new DefaultKey(clsKey);
    }

    public DragData(Map<Key, Set> payload, SafeHtml originalMessage) {
        this.payload = normalize(payload);
        this.originalMessage = originalMessage;
    }

    public SafeHtml getOriginalMessage() {
        return originalMessage;
    }

    public void restoreOriginalMessage(DndDragLeaveEvent evt) {
        evt.getStatusProxy().update(originalMessage);
    }

    /**
     * @see #hasExclusivePayload(DragData.Key[])
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
     * @see #hasExclusivePayload(DragData.Key[])
     */
    public boolean hasExclusivePayload(Collection<Key> keys) {
        return this.payload.size() == keys.size() && hasPayload(keys);
    }

    /**
     * @see #hasPayload(DragData.Key[])
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
     * @see #hasPayload(DragData.Key[])
     */
    public boolean hasPayload(Collection<Key> keys) {
        return this.payload.keySet().containsAll(keys);
    }

    /**
     * @see #getPayload(DragData.Key)
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
    private static SafeHtml generateStatusMessage(Map<Key, Set> payload) {
        String msg = "";
        for (Key k : payload.keySet()) {
            Set s = payload.get(k);
            if (!s.isEmpty()) {
                if (!msg.isEmpty()) msg += ",";
                msg += k.describe(s);
            }
        }
        if (msg.isEmpty()) return SafeHtmlUtils.fromString("<empty>");
        return SafeHtmlUtils.fromString(msg);
    }

    /**
     * A key under which a dragged object can be stored.  Equals() and hashcode() need to be properly implemented.
     */
    public interface Key<T> {
        String describe(Set<T> s);
    }

}
