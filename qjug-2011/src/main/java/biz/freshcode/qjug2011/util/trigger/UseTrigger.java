package biz.freshcode.qjug2011.util.trigger;

import biz.freshcode.qjug2011.util.Ref;
import org.springframework.util.ReflectionUtils;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static biz.freshcode.qjug2011.util.AppObjectUtils.classOf;
import static biz.freshcode.qjug2011.util.AppReflectUtil.*;

public class UseTrigger {
    public static final Method SUPPLIED_METHOD = ReflectionUtils.findMethod(Some.class, "method");
    public static final Object[] SUPPLIED_ARGS = new Object[]{new Object()};

    public static MethodTrigger useTrigger(AbstractButton btn) {
        MethodTrigger t = new MethodTrigger();
        btn.addActionListener(proxy(t, ActionListener.class));
        return t;
    }

    public static <T> MethodTrigger restrictedCapture(T inst, Ref<T> capturer, final Class requiredType, Object... constructorArgs) {
        final MethodTrigger t = new MethodTrigger();
        InvocationHandler ih = new InvocationHandler() {
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                checkReturnType(method, requiredType);
                Object[] args = t.getOriginalArgs();
                graphtArgs(args, method, objects);
                t.trigger(args);
                return defVal(method);
            }
        };
        Class<T> cls = classOf(inst);
        capturer.val = captureInvocation(cls, ih, constructorArgs);
        return t;
    }

    private static void graphtArgs(Object[] args, Method suppliedMethod, Object[] suppliedArgs) {
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg == SUPPLIED_ARGS) args[i] = suppliedArgs;
            else if (arg == SUPPLIED_METHOD) args[i] = suppliedMethod;
        }
    }
}
