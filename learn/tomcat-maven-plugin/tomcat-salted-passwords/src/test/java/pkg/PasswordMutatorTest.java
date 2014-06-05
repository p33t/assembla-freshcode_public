package pkg;

import junit.framework.TestCase;

public class PasswordMutatorTest extends TestCase {
    private PasswordMutator subject = new PasswordMutator();

    public void test() {
        String pwd = "Password" + Math.random();
        String bad = pwd + "x";
        String mute = subject.mutatePassword(pwd);
        assertTrue(subject.verifyMutatedPassword(pwd, mute));
        assertFalse(subject.verifyMutatedPassword(bad, mute));
    }

    public void testNulls() {
        assertNull(subject.mutatePassword(null));
        assertTrue(subject.verifyMutatedPassword(null, null));
    }

    public void testEmptyString() {
        String mute = subject.mutatePassword("");
        assertTrue(subject.verifyMutatedPassword("", mute));
    }
}