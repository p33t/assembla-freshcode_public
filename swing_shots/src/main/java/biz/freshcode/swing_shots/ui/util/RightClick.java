package biz.freshcode.swing_shots.ui.util;

import biz.freshcode.swing_shots.util.trigger.MethodTrigger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static biz.freshcode.swing_shots.util.AppObjectUtils.classOf;
import static biz.freshcode.swing_shots.util.AppReflectUtil.*;

@org.springframework.stereotype.Component
public class RightClick {
    public <T> T menu(Component c, T inst, Object... constructorArgs) {
        SetupRightClick ih = new SetupRightClick(c, inst);
        return captureInvocation(classOf(inst), ih, constructorArgs);
    }

    private static class SetupRightClick implements InvocationHandler {
        private final Component component;
        private final Object inst;

        SetupRightClick(Component component, Object inst) {
            this.component = component;
            this.inst = inst;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] args) throws Throwable {
            checkReturnType(method, JPopupMenu.class);
            MethodTrigger t = new MethodTrigger();
            t.init(inst, method, args);
            component.addMouseListener(new Adapter(component, t));
            return defVal(method);
        }
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
