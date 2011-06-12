package biz.freshcode.swing_shots.ui;

import biz.freshcode.swing_shots.logging.Logging;
import biz.freshcode.swing_shots.ui.util.RightClick;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

import static biz.freshcode.swing_shots.util.trigger.UseTrigger.useTrigger;
import static java.awt.Font.MONOSPACED;

@Component
//@Scope("prototype")
@Lazy(true)
public class TailingPane extends JScrollPane implements InitializingBean {
    @Inject private RightClick rightClick;
    @Logging private Logger log;
    private final JTextArea area;


    public TailingPane() {
        this(new JTextArea(1, 1));
    }

    private TailingPane(JTextArea area) {
        super(area, VERTICAL_SCROLLBAR_NEVER, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.area = area;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setupTextArea();
        rightClick.menuComesFrom(area, this).loadMenu(null);
    }

    public void append(String msg) {
        area.append(msg);
        area.append("\n");
        int len = area.getText().length();
        area.setCaretPosition(len - 2);
    }

    void clear() {
        area.setText("");
    }

    private boolean isEmpty() {
        return area.getText().isEmpty();
    }

    JPopupMenu loadMenu(Point p) {
        JPopupMenu m = new JPopupMenu();
        if (isEmpty()) return m;
        JMenuItem item = m.add("Clear");
        useTrigger(item).toCall(this).clear();
        return m;
    }

    private void setupTextArea() {
        area.setEditable(false);
        Font f = Font.decode(MONOSPACED);
        area.setFont(f);
        DefaultCaret c = (DefaultCaret) area.getCaret();
        c.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
    }
}
