package biz.freshcode.qjug2011.util;

public class AppObjectUtils {
    public static boolean isOneOf(Object o1, Object... o2s) {
        for (Object o2 : o2s) {
            if (o1.equals(o2)) return true;
        }
        return false;
    }
}
