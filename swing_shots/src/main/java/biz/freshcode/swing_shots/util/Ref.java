package biz.freshcode.swing_shots.util;

public class Ref<T> {
    public T val;

    public static <T> Ref<T> ref() {
        return ref(null);
    }

    public static <T> Ref<T> ref(T val) {
        Ref<T> r = new Ref<T>();
        r.val = val;
        return r;
    }
}
