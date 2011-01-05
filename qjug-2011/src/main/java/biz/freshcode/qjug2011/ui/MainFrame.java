package biz.freshcode.qjug2011.ui;

import biz.freshcode.qjug2011.logging.Logging;
import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Component
public class MainFrame extends JFrame {
    @Inject private ConfigurableApplicationContext ctx;
    @Logging private Logger log;

    public MainFrame() {
        setTitle("QJUG 2011 - Peter Leong");
        setupClose();
        setSize(800, 600);
    }

    public void launch() {
        log.info("Launching " + getClass().getSimpleName());
        pack();
        setVisible(true);
    }

    private void setupClose() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                ctx.close();
            }
        });
    }
}
