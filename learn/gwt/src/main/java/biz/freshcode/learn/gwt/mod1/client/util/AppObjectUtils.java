package biz.freshcode.learn.gwt.mod1.client.util;

public class AppObjectUtils {

    /**
     * Null safe comparison of objects.
     */
    public static boolean safeEquals(Object l, Object r) {
        if (l == null) return r == null;
        else return r != null && l.equals(r);
    }
}
