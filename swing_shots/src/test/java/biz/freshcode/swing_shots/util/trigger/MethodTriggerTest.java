package biz.freshcode.swing_shots.util.trigger;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class MethodTriggerTest {
    @Test
    public void testOverrideArgs() {
        MethodTrigger t = new MethodTrigger();
        t.toCall(new MyClass()).say("bad");
        String actual = t.trigger("good");
        assertEquals(actual, "good");
    }

    @Test
    public void testIgnoredArgs() {
        MethodTrigger t = new MethodTrigger();
        t.toCall(new MyClass("good")).hello();
        String actual = t.trigger("bad");
        assertEquals(actual, "good");
    }

    @Test
    public void testConstructorArgs() {
        MethodTrigger t = new MethodTrigger();
        t.toCall(new MyClass("right"), "wrong").hello();
        String actual = t.trigger();
        assertEquals(actual, "right");
    }

    @Test
    public void testInitChecks() {
        MethodTrigger t = new MethodTrigger();
        checkNoTrigger(t);
        t.toCall(new MyClass());
        checkNoTrigger(t);
    }

    private void checkNoTrigger(MethodTrigger t) {
        try {
            t.trigger();
            fail("Should not be allowed to trigger.");
        } catch (MethodTrigger.NotInitializedException e) {
            // expected
        }
    }

    public static class MyClass {
        private final String response;

        public MyClass(String response) {
            this.response = response;
        }

        public MyClass() {
            this("<empty>");
        }

        String hello() {
            return response;
        }

        String say(String msg) {
            return msg;
        }
    }
}
