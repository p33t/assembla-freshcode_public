package biz.freshcode.learn.gwt.client.experiment.forms;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class TimePropertyEditorTest {
    TimePropertyEditor subject = new TimePropertyEditor();
    private static final int MINUTE = 60000;

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
        try {
            subject.parse(str);
            Assert.fail("Did not fail: " + str);
        } catch (ParseException e) {
            // nothing
        }
    }

    private void checkParse(String str, Long expectedMins) throws ParseException {
        Long expected = mins(expectedMins);
        Long actual = subject.parse(str);
        assertEquals(expected, actual);
    }

    private void checkRender(Long mins, String expected) {
        Long time = mins(mins);
        String actual = subject.render(time);
        assertEquals(expected, actual);
    }

    private Long mins(Long mins) {
        return mins == null ? null : mins * 60000;
    }
}
