package biz.freshcode.qjug2011.util.trigger;

import javax.swing.*;
import java.awt.event.ActionListener;

import static biz.freshcode.qjug2011.util.AppReflectUtil.proxy;

public class UseTrigger {
    public static MethodTrigger useTrigger(AbstractButton btn) {
        MethodTrigger t = new MethodTrigger();
        btn.addActionListener(proxy(t, ActionListener.class));
        return t;
    }
}
