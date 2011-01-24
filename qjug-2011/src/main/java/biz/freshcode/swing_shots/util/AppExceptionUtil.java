package biz.freshcode.swing_shots.util;

public class AppExceptionUtil {
    public static RuntimeException runtime(Exception t) {
        if (t instanceof RuntimeException) return (RuntimeException) t;
        return new RuntimeException(t);
    }

    public static IllegalStateException illegalState(String msg) {
        return new IllegalStateException(msg);
    }

    public static IllegalArgumentException illegalArg(String msg) {
        return new IllegalArgumentException(msg);
    }
}
