package biz.freshcode.qjug2011.ui;

import biz.freshcode.qjug2011.logging.Logging;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.awt.Font.MONOSPACED;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER;

@Component
public class MainFrame extends JFrame {
    @Inject private ConfigurableApplicationContext ctx;
    @Logging private Logger log;
    private JButton btn = new JButton("Go");
    private JTextArea area = new JTextArea(1,1);

    public MainFrame() {
        setTitle("QJUG 2011 - Peter Leong");
        setupClose();
        setSize(800, 600);
        populateUi();
    }

    private void populateUi() {
        JScrollPane textPane = setupTextArea();

        FormLayout layout = new FormLayout(
                "right:pref, 3dlu, pref, pref:grow", // cols
                "p, 3dlu, p, 3dlu, p, fill:pref:grow, 3dlu"); // rows
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints c = new CellConstraints();
        int fullWidth = layout.getColumnCount() + 1;
        int row = 0;
        int col;
        builder.addSeparator("Complicated", c.xyw(col = 1, row += 1, fullWidth - col));
        builder.addLabel("Button:", c.xy(1, row += 2));
        builder.add(btn, c.xy(3, row));
        builder.addLabel("Output:", c.xy(1, row += 2));
        builder.add(textPane, c.xyw(col = 1, row += 1, fullWidth - col));

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(builder.getPanel(), BorderLayout.CENTER);
    }

    private JScrollPane setupTextArea() {
        area.setEditable(false);
        Font f = Font.decode(MONOSPACED);
        area.setFont(f);
        DefaultCaret c = (DefaultCaret) area.getCaret();
        c.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        return new JScrollPane(area, VERTICAL_SCROLLBAR_NEVER, HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
