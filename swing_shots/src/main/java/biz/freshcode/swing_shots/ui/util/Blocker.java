package biz.freshcode.swing_shots.ui.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.Cursor.WAIT_CURSOR;

public class Blocker extends JPanel {
    private final JFrame owner;
    private java.awt.Component focusOwnerOrNull;

    public Blocker(JFrame f) {
        owner = f;
        f.setGlassPane(this);
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(WAIT_CURSOR));
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                e.consume();
            }
        });
        addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                if (e.isTemporary()) return;
                if (!isVisible()) return;
                requestFocus();
            }
        });
    }

    public static Blocker blockerFor(JFrame f) {
        if (f.getGlassPane() instanceof Blocker) return (Blocker) f.getGlassPane();
        return new Blocker(f);
    }

    public void block() {
        focusOwnerOrNull = owner.getFocusOwner();
        setVisible(true);
        requestFocusInWindow();
    }

    public void unblock() {
        setVisible(false);
        if (focusOwnerOrNull == null) return;
        focusOwnerOrNull.requestFocus();
        focusOwnerOrNull = null;
    }
}
