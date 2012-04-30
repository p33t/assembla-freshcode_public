package pkg.b_generation;

import java.lang.reflect.Method;

class Probe {
    static final Method GENERIC_METHOD;

    static {
        try {
            GENERIC_METHOD = Probe.class.getMethod("generic", new Class[]{Object.class});
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * For gleaning generics information.
     */
    public <T> T generic(T t) {
        return t;
    }
}
