package biz.freshcode.qjug2011.ui;

import biz.freshcode.qjug2011.util.trigger.MethodTrigger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static biz.freshcode.qjug2011.util.AppExceptionUtil.illegalArg;
import static biz.freshcode.qjug2011.util.AppObjectUtils.classOf;
import static biz.freshcode.qjug2011.util.AppReflectUtil.captureInvocation;

public class RightClick {
    public static <T> T rightClickMenu(final Component c, final T inst, Object... constructorArgs) {
        InvocationHandler ih = new InvocationHandler() {
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                checkReturnType(method, JPopupMenu.class);
                MethodTrigger t = new MethodTrigger();
                t.init(inst, method, objects);
                c.addMouseListener(new Adapter(c, t));
                return null;
            }
        };
        Class<T> cls = classOf(inst);
        return captureInvocation(cls, ih, constructorArgs);
    }

    private static void checkReturnType(Method method, Class cls) {
        boolean isCompatible = cls.isAssignableFrom(method.getReturnType());
        if (!isCompatible) throw illegalArg("The specified method " + method + " does not return a " + cls.getSimpleName());
    }

    private static class Adapter extends MouseAdapter {
        private final Component component;
        private final MethodTrigger trigger;

        public Adapter(Component c, MethodTrigger t) {
            component = c;
            trigger = t;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            attemptPopup(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            attemptPopup(e);
        }

        private void attemptPopup(MouseEvent e) {
            if (!e.isPopupTrigger()) return;
            JPopupMenu mnu = trigger.trigger(e.getPoint());
            if (mnu == null) return;
            if (mnu.getSubElements().length == 0) return;
            mnu.show(component, e.getX(), e.getY());
        }
    }
}
