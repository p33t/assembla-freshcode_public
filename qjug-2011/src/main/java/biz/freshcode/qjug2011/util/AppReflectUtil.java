package biz.freshcode.qjug2011.util;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static biz.freshcode.qjug2011.util.AppArrayUtils.arr;
import static biz.freshcode.qjug2011.util.AppExceptionUtil.illegalArg;
import static biz.freshcode.qjug2011.util.AppExceptionUtil.runtime;
import static java.lang.ClassLoader.getSystemClassLoader;
import static java.lang.reflect.Proxy.newProxyInstance;

public class AppReflectUtil {

    public static Object invokeMethod(Method m, Object inst, Object... args) {
        m.setAccessible(true);
        return ReflectionUtils.invokeMethod(m, inst, args);
    }

    public static <T> T captureInvocation(Class<T> cls, InvocationHandler handler, Object... constructorArgs) {
        Adapter a = new Adapter(handler);
        if (cls.isInterface()) return proxy(a, cls);
        return subclass(a, cls, constructorArgs);
    }

    public static Object defVal(Method m) {
        return defVal(m.getReturnType());
    }

    public static Object defVal(Class cls) {
        if (Boolean.TYPE.equals(cls)) return Boolean.FALSE;
        if (Byte.TYPE.equals(cls)) return (byte) 0;
        if (Short.TYPE.equals(cls)) return (short) 0;
        if (Integer.TYPE.equals(cls)) return 0;
        if (Long.TYPE.equals(cls)) return 0L;
        if (Float.TYPE.equals(cls)) return 0f;
        if (Double.TYPE.equals(cls)) return 0.0;
        if (Character.TYPE.equals(cls)) return '\u0000';
        return null;
    }

    public static boolean matches(Method m, Object[] args) {
        Class[] types = m.getParameterTypes();
        return matches(args, types);
    }

    private static boolean matches(Constructor c, Object[] args) {
        Class[] types = c.getParameterTypes();
        return matches(args, types);
    }

    private static boolean matches(Object[] args, Class[] types) {
        if (types.length != args.length) return false;
        for (int i = 0; i < types.length; i++) {
            Object arg = args[i];
            if (arg == null) continue;
            Class argClass = arg.getClass();
            if (!types[i].isAssignableFrom(argClass)) return false;
        }
        return true;
    }

    public static <T> T proxy(InvocationHandler ih, Class<T> iface) {
        ClassLoader l = findLoader(ih.getClass());
        return iface.cast(newProxyInstance(l, arr(iface), ih));
    }

    private static ClassLoader findLoader(Class cls) {
        ClassLoader loader = cls.getClassLoader();
        boolean useDefault = loader == null || loader.equals(getSystemClassLoader());
        if (useDefault) loader = AppReflectUtil.class.getClassLoader();
        return loader;
    }

    private static <T> T subclass(Adapter adapter, Class<T> cls, Object... contructorArgs) {
        Object[] args = contructorArgs;
        Class[] signature = null;
        Constructor<?>[] ctors = cls.getConstructors();
        for (Constructor c : ctors) {
            if (matches(c, args)) signature = c.getParameterTypes();
        }
        if (signature == null && ctors.length == 1 && args.length == 0) {
            // try using defaults
            signature = ctors[0].getParameterTypes();
            args = defArgs(signature);
        }
        if (signature == null) throw illegalArg("Unable to find a constructor to match the given arguments.");
        ProxyFactory f = new ProxyFactory();
        f.setSuperclass(cls);
        try {
            adapter.disableDivert();
            Object sub = f.create(signature, args, adapter);
            adapter.enableDivert();
            return cls.cast(sub);
        } catch (Exception e) {
            throw runtime(e);
        }
    }

    private static Object[] defArgs(Class[] signature) {
        Object[] args = new Object[signature.length];
        for (int i = 0; i < signature.length; i++) {
            Class type = signature[i];
            args[i] = defVal(type);
        }
        return args;
    }

    public static void checkReturnType(Method method, Class cls) {
        boolean isCompatible = cls.isAssignableFrom(method.getReturnType());
        if (!isCompatible) throw illegalArg("The specified method " + method + " does not return a " + cls.getSimpleName());
    }

    private static class Adapter implements MethodHandler, InvocationHandler {
        private final InvocationHandler delegate;
        private boolean doDivert = true;

        public void enableDivert() {
            doDivert = true;
        }

        public void disableDivert() {
            doDivert = false;
        }

        public Adapter(InvocationHandler delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
            if (doDivert(thisMethod)) divert(thisMethod, args);
            else if (proceed != null) return invokeMethod(proceed, self, args);
            // else not diverting and cannot proceed
            return defVal(thisMethod);
        }

        private boolean doDivert(Method m) {
            return doDivert && !isIgnored(m);
        }

        private boolean isIgnored(Method m) {
            String name = m.getName();
            return name.equals("getClass") || name.equals("finalize");
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            return invoke(null, method, null, objects);
        }

        private void divert(Method method, Object[] objects) throws Throwable {
            doDivert = false;
            delegate.invoke(null, method, objects);
        }
    }
}
