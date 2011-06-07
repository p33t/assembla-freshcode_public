package biz.freshcode.swing_shots.ui.util;

import org.testng.annotations.Test;

public class HourglassTest {

    @Test
    public void testThreadCheck() {
        Hourglass h = new Hourglass();
        try {
            h.surround(new Hourglass.Worker() {
                public void doInBackground() {
                    // nothing
                }

                public void done() {
                    // nothing
                }
            });
        } catch (IllegalStateException ex) {
            // expected
        }
    }
}
