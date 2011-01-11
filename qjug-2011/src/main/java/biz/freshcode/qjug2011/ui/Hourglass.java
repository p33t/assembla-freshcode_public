package biz.freshcode.qjug2011.ui;

import biz.freshcode.qjug2011.util.AppReflectUtil;
import biz.freshcode.qjug2011.util.Ref;
import biz.freshcode.qjug2011.util.trigger.MethodTrigger;
import biz.freshcode.qjug2011.util.trigger.ProxyProvider;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.swing.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import static biz.freshcode.qjug2011.ui.Blocker.blockerFor;
import static biz.freshcode.qjug2011.util.AppExceptionUtil.illegalArg;
import static biz.freshcode.qjug2011.util.AppReflectUtil.invokeMethod;
import static biz.freshcode.qjug2011.util.Ref.ref;
import static biz.freshcode.qjug2011.util.trigger.UseTrigger.*;

@Component
public class Hourglass implements ProxyProvider {
    @Inject private FrameRegistry frameReg;

    @Override
    public <T> T proxy(final Object obj, final Class<T> iface) {
        InvocationHandler ih = new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method m, Object[] objects) throws Throwable {
                if (!declaredOn(m, iface)) return m.invoke(obj, objects);
                if (!voidReturn(m)) throw illegalArg("Can only put hourglass around void return types.");
                MethodTrigger t = new MethodTrigger();
                t.init(obj, m, objects);
                surround(t).run();
                return null;
            }
        };
        return AppReflectUtil.proxy(ih, iface);
    }

    public <T> T surround(T inst, Object... constructorArgs) {
        // TODO: Thread and serial access checks.
        Ref<T> r = ref();
        MethodTrigger t = restrictedCapture(inst, r, Void.TYPE, constructorArgs);
        t.toCall(this).surround(inst, SUPPLIED_METHOD, SUPPLIED_ARGS);
        return r.val;
    }

    private boolean declaredOn(Method m, Class iface) {
        return iface.equals(m.getDeclaringClass());
    }

    // TODO: Use swing workers.
    <T> void surround(T inst, Method m, Object[] args) {
        List<JFrame> frames = frameReg.listFrames();
        for (JFrame frame : frames) {
            blockerFor(frame).block();
        }
        try {
            invokeMethod(m, inst, args);
        } finally {
            for (JFrame frame : frames) {
                blockerFor(frame).unblock();
            }
        }
    }

    private boolean voidReturn(Method method) {
        return Void.TYPE.equals(method.getReturnType());
    }
}
