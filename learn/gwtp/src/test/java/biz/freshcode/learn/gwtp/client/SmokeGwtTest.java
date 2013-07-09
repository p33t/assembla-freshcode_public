package biz.freshcode.learn.gwtp.client;

import biz.freshcode.learn.gwtp.client.test.TestUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;

public class SmokeGwtTest extends GWTTestCase {
    @Override
    public String getModuleName() {
        return TestUtil.MODULE_NAME;
    }

    public void test() {
        assertTrue(GWT.isClient());
    }
}
