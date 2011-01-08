package biz.freshcode.qjug2011.ui;

import biz.freshcode.qjug2011.logging.Logging;
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
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import static biz.freshcode.qjug2011.ui.RightClick.rightClickMenu;
import static biz.freshcode.qjug2011.util.trigger.UseTrigger.useTrigger;
import static java.awt.Font.MONOSPACED;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER;

@Component
@Lazy(true) // prevents errors on a headless CI box.
public class MainFrame extends JFrame implements InitializingBean {
    @Inject private ConfigurableApplicationContext ctx;
    @Logging private Logger log;
    private JButton btnGo = new JButton("Go");
    private JTextArea area = new JTextArea(1,1);
    private JCheckBox chkBackground = new JCheckBox("Background Task");

    @Override
    public void afterPropertiesSet() throws Exception {
        setTitle("QJUG 2011 - Peter Leong");
        setupClose();
        populateUi();
        useTrigger(btnGo).toCall(this).go();
        useTrigger(chkBackground).toCall(this).toggleWorker();
        rightClickMenu(area, this).loadMenu();
    }

    JPopupMenu loadMenu() {
        log.info("Load Menu at " + new Date());
        return null;
    }

    public void launch() {
        log.info("Launching " + getClass().getSimpleName());
        pack();
        setSize(800, 600);
        setVisible(true);
    }

    void go() {
        log.info("Go pressed at " + new Date());
    }

    private void populateUi() {
        JScrollPane textPane = setupTextArea();

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
        builder.addLabel("Output:", c.xy(1, row += 2));
        builder.add(textPane, c.xyw(col = 1, row += 1, fullWidth - col));

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(builder.getPanel(), BorderLayout.CENTER);
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

    private JScrollPane setupTextArea() {
        area.setEditable(false);
        Font f = Font.decode(MONOSPACED);
        area.setFont(f);
        DefaultCaret c = (DefaultCaret) area.getCaret();
        c.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        return new JScrollPane(area, VERTICAL_SCROLLBAR_NEVER, HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    void toggleWorker() {
        log.info("Toggle worker at " + new Date());
    }
}
