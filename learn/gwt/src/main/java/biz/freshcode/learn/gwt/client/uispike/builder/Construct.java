package biz.freshcode.learn.gwt.client.uispike.builder;

/**
 * Facilitates construction closures in builders.
 */
public abstract class Construct<T> {
    public T builder;

    public abstract void run();

    public static class Parent<T> {
        public T construct(Construct<T> c) {
            @SuppressWarnings("unchecked")
            T t = (T) this;
            c.builder = t;
            c.run();
            return t;
        }
    }
}
