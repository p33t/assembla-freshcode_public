package biz.freshcode.swing_shots.ui;

import biz.freshcode.swing_shots.ui.util.Hourglass;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.swing.*;

import static biz.freshcode.swing_shots.util.trigger.UseTrigger.useTrigger;

/**
 * The menu can get very complex.  This helps break up the code.
 * NOTE: This didn't have to be a 'prototype'.  It just illustrates the concept.
 */
@Component
@Scope("prototype")
public class MenuBar extends JMenuBar implements InitializingBean {
    private final Host host;
    @Inject private Hourglass hourglass;

    public MenuBar(Host host) {
        this.host = host;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        JMenu mnu = new JMenu("The Menu");
        useTrigger(mnu.add("Dialog Demo"), hourglass).toCall(host).dialogDemo();
        add(mnu);
    }

    public interface Host {
        void dialogDemo();
    }
}
