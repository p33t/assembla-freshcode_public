package biz.freshcode.learn.gwtp.shared.util;

public class ExceptionUtil {
    public static IllegalArgumentException illegalArg(String msg) {
        return new IllegalArgumentException(msg);
    }

    public static IllegalStateException illegalState(String s) {
        return new IllegalStateException(s);
    }
}
