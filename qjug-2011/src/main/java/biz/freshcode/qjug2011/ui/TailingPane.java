package biz.freshcode.qjug2011.ui;

import biz.freshcode.qjug2011.logging.Logging;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

import static java.awt.Font.MONOSPACED;

@Component
@Scope("prototype")
public class TailingPane extends JScrollPane implements InitializingBean{
    @Inject private RightClick rightClick;
    @Logging private Logger log;
    private final JTextArea area;

    
    public TailingPane() {
        this(new JTextArea(1,1));
    }

    private TailingPane(JTextArea area) {
        super(area , VERTICAL_SCROLLBAR_NEVER, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.area = area;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setupTextArea();
        rightClick.menu(area, this).loadMenu(null);
    }

    JPopupMenu loadMenu(Point p) {
        log.info("Right click at " + p);
        return null;
    }

    private void setupTextArea() {
        area.setEditable(false);
        Font f = Font.decode(MONOSPACED);
        area.setFont(f);
        DefaultCaret c = (DefaultCaret) area.getCaret();
        c.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
    }

    public void append(String msg) {
        area.append(msg);
        area.append("\n");
        int len = area.getText().length();
        area.setCaretPosition(len - 2);
    }
}
