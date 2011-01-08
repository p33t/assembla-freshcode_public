package biz.freshcode.qjug2011.ui;

import biz.freshcode.qjug2011.util.Ref;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

import static biz.freshcode.qjug2011.util.Ref.ref;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

public class RightClickTest {
    RightClick subject = new RightClick();

    @Test
    public void testBadMethod() {
        try {
            subject.menu(new JButton(), this).badMenu();
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
        subject.menu(c, this).loadMenu();
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
