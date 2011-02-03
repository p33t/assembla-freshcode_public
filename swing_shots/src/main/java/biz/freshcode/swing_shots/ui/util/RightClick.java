package biz.freshcode.swing_shots.ui.util;

import biz.freshcode.swing_shots.util.Ref;
import biz.freshcode.swing_shots.util.trigger.MethodTrigger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;

import static biz.freshcode.swing_shots.util.Ref.ref;
import static biz.freshcode.swing_shots.util.trigger.UseTrigger.*;

@org.springframework.stereotype.Component
public class RightClick {
    public <T> T menu(final Component c, final T inst, Object... constructorArgs) {
        Ref<T> r = ref();
        restrictedCapture(inst, r, JPopupMenu.class).toCall(this).setupRightClick(c, inst, SUPPLIED_METHOD, SUPPLIED_ARGS);
        return r.val;
    }

    void setupRightClick(Component c, Object inst, Method method, Object[] suppliedArgs) {
        MethodTrigger t = new MethodTrigger();
        t.init(inst, method, suppliedArgs);
        c.addMouseListener(new Adapter(c, t));
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
