package biz.freshcode.learn.gwt2.common.client.util;

public class AppObjectUtils {

    /**
     * Null safe comparison of objects.
     */
    public static boolean safeEquals(Object l, Object r) {
        if (l == null) return r == null;
        else return r != null && l.equals(r);
    }

    public static <T> T ifNull(T t, T alt) {
        if (t == null) return alt;
        return t;
    }
}
