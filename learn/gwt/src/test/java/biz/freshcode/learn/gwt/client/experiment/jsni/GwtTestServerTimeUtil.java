package biz.freshcode.learn.gwt.client.experiment.jsni;

import biz.freshcode.learn.gwt.client.test.TestUtil;
import com.google.gwt.junit.client.GWTTestCase;

public class GwtTestServerTimeUtil extends GWTTestCase {

    public void test() {
        assertEquals(1234, ServerTimeUtil.serverTimeDiff());
    }

    @Override
    public String getModuleName() {
        return TestUtil.MODULE_NAME;
    }
}
