package biz.freshcode.qjug2011.util.trigger;

import biz.freshcode.qjug2011.util.Ref;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static biz.freshcode.qjug2011.util.AppObjectUtils.classOf;
import static biz.freshcode.qjug2011.util.AppReflectUtil.*;

public class UseTrigger {
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

    private static void graphtArgs(Object[] args, Method method, Object[] objects) {
        int len = args.length;
        args[len - 2] = method;
        args[len - 1] = objects;
    }
}
