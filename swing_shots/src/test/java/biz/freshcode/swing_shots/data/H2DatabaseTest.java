package biz.freshcode.swing_shots.data;

import org.testng.annotations.Test;

import static biz.freshcode.swing_shots.test.TestBootstrap.TEST_CONTEXT;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class H2DatabaseTest {
    H2Database h2 = TEST_CONTEXT.getBean(H2Database.class);

    @Test
    public void testOpenClose() {
        assertFalse(h2.isOpen());
        h2.openExisting();
        assertTrue(h2.isOpen());
        h2.close();
        assertFalse(h2.isOpen());
    }
}
