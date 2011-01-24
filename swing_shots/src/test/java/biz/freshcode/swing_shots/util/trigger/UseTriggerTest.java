package biz.freshcode.swing_shots.util.trigger;

import biz.freshcode.swing_shots.util.Ref;
import org.testng.annotations.Test;

import javax.swing.*;

import static biz.freshcode.swing_shots.util.Ref.ref;
import static biz.freshcode.swing_shots.util.trigger.UseTrigger.useTrigger;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UseTriggerTest {
    Ref<String> actualRef = ref();

    @Test
    public void testBtn() {
        JButton btn = new JButton();
        useTrigger(btn).toCall(this).hello("bruce lee");
        actualRef.val = null;
        btn.doClick();
        assertEquals(actualRef.val, "bruce lee");
    }

    @Test
    public void testVoid() {
       assertTrue(Void.class.isAssignableFrom(Void.class));
    }

    void hello(String arg) {
        actualRef.val = arg;
    }
}
