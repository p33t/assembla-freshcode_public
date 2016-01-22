package pkg.builder;

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