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
    /**
     * Setups up a right click menu source for the supplied component.
     *
     * @param c               The component that will be the source of the right click event.
     * @param inst            The object whose method will supply the JPopupMenu
     * @param constructorArgs Any arguments needed to create a new dummy instance of the 'inst' object for subclassing.
     * @param <T>             The type of 'inst'.
     * @return The dummy subclass used to designate the method that will supply the JPopupMenu.
     */
    public <T> T menuComesFrom(Component c, T inst, Object... constructorArgs) {
        SetupRightClick ih = new SetupRightClick(c, inst);
        return captureInvocation(classOf(inst), ih, constructorArgs);
    }

    /* *
     * Another way to set up right click that doesn't check the return type of the designated JPopupMenu source method.
     * @param c The component that is the source of the right click event.
     * @return A MethodTrigger used to designated the source of the menu.
     */
//    public MethodTrigger on(Component c) {
//        MethodTrigger t = new MethodTrigger();
//        c.addMouseListener(new Adapter(c, t));
//        return t;
//    }

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
