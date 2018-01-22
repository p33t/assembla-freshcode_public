package pkg.junit;

import junit.framework.TestCase;

public class BasicTest extends TestCase {
    public void testThis() {
        // looks like need to prefix with 'test'; @Test doesn't work.  Am using 'vintage' junit.
        assertEquals(99, 99);
    }
}
