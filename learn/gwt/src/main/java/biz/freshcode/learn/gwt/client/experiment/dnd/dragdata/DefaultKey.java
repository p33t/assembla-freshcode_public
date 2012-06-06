package biz.freshcode.learn.gwt.client.experiment.dnd.dragdata;

import java.util.Set;

class DefaultKey<T> implements DragData.Key<T> {
    private final Class<T> cls;

    public DefaultKey(Class<T> cls) {
        this.cls = cls;
    }

    /**
     * Minimal implementation which results in, say, '1xApple'
     */
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
