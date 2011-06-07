package biz.freshcode.swing_shots.ui;

import biz.freshcode.swing_shots.config.Factory;
import biz.freshcode.swing_shots.logging.Logging;
import biz.freshcode.swing_shots.ui.util.Hourglass;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static biz.freshcode.swing_shots.util.AppStringUtil.repeat;
import static biz.freshcode.swing_shots.util.AppThreadUtil.sleep;
import static biz.freshcode.swing_shots.util.trigger.UseTrigger.useTrigger;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showConfirmDialog;

@Component
@Lazy(true) // prevents errors on a headless CI box.
public class MainFrame extends JFrame implements InitializingBean, MenuBar.Host {
    @Inject private ConfigurableApplicationContext ctx;
    @Inject private Hourglass hourglass;
    @Inject private TailingPane tailer;
    @Inject private BackgroundWorker worker;
    @Inject private Factory factory;
    @Logging private Logger log;
    private JButton btnGo = new JButton("Go");
    private JCheckBox chkBackground = new JCheckBox("Background Task");

    @Override
    public void afterPropertiesSet() throws Exception {
        setTitle("Swing Shots - Peter Leong");
        setupClose();
        populateUi();
        useTrigger(btnGo, hourglass).toCall(this).go();
        useTrigger(chkBackground, hourglass).toCall(this).toggleWorker();
    }

    public void launch() {
        log.info("Launching " + getClass().getSimpleName());
        pack();
        setSize(800, 600);
        setVisible(true);
    }

    void go() {
        for (int i = 0; i < 10; i++) {
            sleep(500);
            tailer.append(repeat("z", i + 1));
        }
    }

    void toggleWorker() {
        if (chkBackground.isSelected()) worker.activate();
        else worker.deactivate();
    }

    private void populateUi() {
        FormLayout layout = new FormLayout(
                "right:pref, 3dlu, pref, pref:grow", // cols
                "p, 3dlu, p, 3dlu, p, 3dlu, p, fill:pref:grow, 3dlu"); // rows
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints c = new CellConstraints();
        int fullWidth = layout.getColumnCount() + 1;
        int row = 0;
        int col;
        builder.addSeparator("Complicated", c.xyw(col = 1, row += 1, fullWidth - col));
        builder.addLabel("Button:", c.xy(1, row += 2));
        builder.add(btnGo, c.xy(3, row));
        builder.add(chkBackground, c.xy(3, row += 2));
        builder.addLabel("Output (right-click to clear):", c.xyw(1, row += 2, 3));
        builder.add(tailer, c.xyw(col = 1, row += 1, fullWidth - col));

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(builder.getPanel(), BorderLayout.CENTER);

        cp.add(factory.menuBar(this), BorderLayout.NORTH);
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

    @Override
    public void dialogDemo() {
        boolean confirmed = showConfirmDialog(this
                , "Perform some long operation?"
                , "Confirm"
                , OK_CANCEL_OPTION
                , WARNING_MESSAGE) == JOptionPane.OK_OPTION;
        tailer.append(confirmed ? "==== Operation Started..." : "==== Operation Aborted");
        if (confirmed) {
            hourglass.surround(new Hourglass.Worker() {
                public void doInBackground() {
                    sleep(3000);
                }

                public void done() {
                    tailer.append("==== Operation finished.");
                }
            });
        }
    }
}
