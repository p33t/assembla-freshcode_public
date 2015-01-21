package biz.freshcode.learn.gwt.mod1.client.experiment.forms;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class HrMinConverterTest {
    HrMinConverter subject = new HrMinConverter();

    @Test
    public void testParse() throws Exception {
        checkParse("0", 0L);
        checkParse("0:0", 0L);
        checkParse("-0", 0L);
        checkParse("", null);
        checkParse(null, null);
        checkParse("1:0", 60L);
        checkParse("-99:9", -(99L * 60 + 9));
        checkBadParse("x");
        checkBadParse(":1");
        checkBadParse("0:60");
        checkBadParse("-:10");
    }

    @SuppressWarnings({"NullableProblems"})
    @Test
    public void testRender() throws Exception {
        checkRender(null, "");
        checkRender(0L, "0:00");
        checkRender(60L, "1:00");
        checkRender(-1L, "-0:01");
        checkRender(70L, "1:10");
    }

    private void checkBadParse(String str) {
        Assert.assertNull(subject.convertFieldValue(str));
//         Not really sure how non-parsable strings are handled
//        try {
//            subject.convertFieldValue(str);
//            Assert.fail("Did not fail: " + str);
//        } catch (ParseException e) {
//            nothing
//        }
    }

    private void checkParse(String str, Long expectedMins) throws ParseException {
        Long expected = mins(expectedMins);
        Long actual = subject.convertFieldValue(str);
        assertEquals(expected, actual);
    }

    private void checkRender(Long mins, String expected) {
        Long time = mins(mins);
        String actual = subject.convertModelValue(time);
        assertEquals(expected, actual);
    }

    private Long mins(Long mins) {
        return mins == null ? null : mins * 60000;
    }
}
