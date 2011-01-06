package biz.freshcode.qjug2011.util;

import java.lang.reflect.Method;

public class Invocation {
    public final Method method;
    public final Object[] args;

    public Invocation(Method method, Object[] args) {
        this.method = method;
        this.args = args;
    }
}
