package pkg.regexp;

import com.google.gwt.regexp.shared.RegExp;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BasicRegexpTest {
    @Test
    public void timeParse() {
        RegExp re = RegExp.compile("^(-?)(\\d+)(:([0-5]?)(\\d))?$");
        assertTrue(re.test("99"));
        assertTrue(re.test("-99"));
    }
}
