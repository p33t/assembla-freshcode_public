package biz.freshcode.qjug2011.util;

public class Ref<T> {
    public static <T> Ref<T> ref(T val) {
        Ref<T> r = new Ref<T>();
        r.val = val;
        return r;
    }

    public static <T> Ref<T> ref() {
        return ref(null);
    }

    public T val;
}
