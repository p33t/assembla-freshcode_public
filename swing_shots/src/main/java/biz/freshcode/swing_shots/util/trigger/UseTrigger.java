package biz.freshcode.swing_shots.util.trigger;

import javax.swing.*;
import java.awt.event.ActionListener;

import static biz.freshcode.swing_shots.util.AppReflectUtil.proxy;

public class UseTrigger {
    public static MethodTrigger useTrigger(AbstractButton btn) {
        return useTrigger(btn, null);
    }

    public static MethodTrigger useTrigger(AbstractButton btn, ProxyProvider ppOrNull) {
        MethodTrigger t = new MethodTrigger();
        ActionListener listener = proxy(t, ActionListener.class);
        if (ppOrNull != null) listener = ppOrNull.proxy(listener, ActionListener.class);
        btn.addActionListener(listener);
        return t;
    }
}
