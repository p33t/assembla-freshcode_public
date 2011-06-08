package biz.freshcode.swing_shots.ui.util;

import biz.freshcode.swing_shots.util.AppReflectUtil;
import biz.freshcode.swing_shots.util.trigger.ProxyProvider;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.swing.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import static biz.freshcode.swing_shots.ui.util.Blocker.blockerFor;
import static biz.freshcode.swing_shots.util.AppExceptionUtil.illegalArg;
import static biz.freshcode.swing_shots.util.AppExceptionUtil.illegalState;
import static biz.freshcode.swing_shots.util.AppReflectUtil.invokeMethod;
import static javax.swing.SwingUtilities.isEventDispatchThread;

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

    public void surround(final Runnable r) {
        Worker w = new Worker() {
            @Override
            public void doInBackground() {
                r.run();
            }

            @Override
            public void done() {
                // nothing
            }
        };
        surround(w);
    }

    /**
     * Display a wait cursor for the specified operation and then perform a callback.
     * This can be used for complex operations that, upon completion need to update the screen.
     * It is derived from SwingWorker functionality.
     *
     * @see javax.swing.SwingWorker
     */
    public void surround(final Worker w) {
        if (!isEventDispatchThread()) throw illegalState("Can only operate hourglass from event dispatch thread.");
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

    private boolean declaredOn(Method m, Class iface) {
        return iface.equals(m.getDeclaringClass());
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

    /**
     * Defines an operation to put an hourglass around.
     *
     * @see Hourglass#surround(biz.freshcode.swing_shots.ui.util.Hourglass.Worker)
     */
    public static interface Worker {
        /**
         * Performed on background thread while hourglass is showing.
         */
        void doInBackground();

        /**
         * Performed on event dispatch thread while hourglass is showing,
         * after the background task has completed.
         */
        void done();
    }
}
