package biz.freshcode.swing_shots.ui.util;

import biz.freshcode.swing_shots.util.AppReflectUtil;
import biz.freshcode.swing_shots.util.Ref;
import biz.freshcode.swing_shots.util.trigger.MethodTrigger;
import biz.freshcode.swing_shots.util.trigger.ProxyProvider;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.swing.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import static biz.freshcode.swing_shots.ui.util.Blocker.blockerFor;
import static biz.freshcode.swing_shots.util.AppExceptionUtil.illegalArg;
import static biz.freshcode.swing_shots.util.AppReflectUtil.invokeMethod;
import static biz.freshcode.swing_shots.util.Ref.ref;
import static biz.freshcode.swing_shots.util.trigger.UseTrigger.*;

@Component
public class Hourglass implements ProxyProvider {
    @Inject private FrameRegistry frameReg;

    // TODO: todo(Runnable)

    @Override
    public <T> T proxy(final Object delegate, final Class<T> iface) {
        InvocationHandler ih = new InvocationHandler() {
            @Override
            public Object invoke(Object o, final Method m, final Object[] objects) throws Throwable {
                if (!declaredOn(m, iface)) return m.invoke(delegate, objects);
                if (!voidReturn(m)) throw illegalArg("Can only put hourglass around void return types.");
                Worker w = new Worker() {
                    @Override
                    public void doInBackground() {
                        invokeMethod(m, delegate, objects);
                    }

                    @Override
                    public void done() {
                        // nothing
                    }
                };
                surround(w);
                return null;
            }
        };
        return AppReflectUtil.proxy(ih, iface);
    }

    public <T> T surround(T inst, Object... constructorArgs) {
        Ref<T> r = ref();
        MethodTrigger t = restrictedCapture(inst, r, Void.TYPE, constructorArgs);
        t.toCall(this).surround(inst, SUPPLIED_METHOD, SUPPLIED_ARGS);
        return r.val;
    }

    private boolean declaredOn(Method m, Class iface) {
        return iface.equals(m.getDeclaringClass());
    }

    void surround(final Object inst, final Method m, final Object[] args) {
        Worker w = new Worker() {
            @Override
            public void doInBackground() {
                invokeMethod(m, inst, args);
            }

            @Override
            public void done() {
                // nothing
            }
        };
    }

    public void surround(final Worker w) {
        // TODO: Thread and serial access checks.
        final List<JFrame> frames = frameReg.listFrames();
        // TODO: Conditional checks in case nesting?
        block(frames);
        new SwingWorker() {
            @Override
            protected Object doInBackground() {
                w.doInBackground();
                return null;
            }

            @Override
            protected void done() {
                w.done();
                unblock(frames);
                super.done();
            }
        }.execute();
    }

    private void unblock(List<JFrame> frames) {
        for (JFrame frame : frames) {
            blockerFor(frame).unblock();
        }
    }

    private void block(List<JFrame> frames) {
        for (JFrame frame : frames) {
            blockerFor(frame).block();
        }
    }

    private boolean voidReturn(Method method) {
        return Void.TYPE.equals(method.getReturnType());
    }

    public static interface Worker {
        void doInBackground();

        void done();
    }
}
