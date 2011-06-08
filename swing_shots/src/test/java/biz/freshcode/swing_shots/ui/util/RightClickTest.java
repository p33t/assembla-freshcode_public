package biz.freshcode.swing_shots.ui.util;

import biz.freshcode.swing_shots.util.Ref;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

import static biz.freshcode.swing_shots.util.Ref.ref;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

public class RightClickTest {
    RightClick subject = new RightClick();

    @Test
    public void testBadMethod() {
        try {
            subject.menuComesFrom(new JButton(), this).badMenu();
            fail("Did not check method return type.");
        } catch (IllegalArgumentException e) {
            //expected
        }
    }

    @Test
    public void testGoodMethod() {
        final Ref<MouseListener> r = ref();
        Component c = new Component() {
            @Override
            public void addMouseListener(MouseListener listener) {
                r.val = listener;
                super.addMouseListener(listener);
            }
        };
        subject.menuComesFrom(c, this).loadMenu();
        // Hmm... don't know how to test this.
        assertNotNull(r.val);
    }

    public JPopupMenu loadMenu() {
        return new JPopupMenu();
    }

    public JMenuItem badMenu() {
        return null;
    }
}
