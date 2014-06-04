package pkg;

import junit.framework.TestCase;

public class PasswordMutationServiceImplTest extends TestCase {
    private PasswordMutationService subject = new PasswordMutationServiceImpl();

    public void test() {
        String pwd = "Password" + Math.random();
        String bad = pwd + "x";
        String mute = subject.mutatePassword(pwd);
        assertTrue(subject.verifyMutatedPassword(pwd, mute));
        assertFalse(subject.verifyMutatedPassword(bad, mute));
    }
}