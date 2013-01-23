package pkg.mock.mockito;

import org.junit.Test;
import org.mockito.exceptions.verification.WantedButNotInvoked;
import pkg.common.Clazz;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FirstTest {

    @Test
    public void basic() {
        Clazz mock = mock(Clazz.class);
        mock.getNum();
        verify(mock).getNum();



        try {
            verify(mock).getStr();
        }
        catch (WantedButNotInvoked e) {
            // expected
        }
    }
}
