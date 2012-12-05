package biz.freshcode.learn.gwt.client.experiment.jsni;

import com.google.gwt.junit.client.GWTTestCase;

public class ServerTimeUtilTest extends GWTTestCase {

    public void test() {
        assertEquals(1234, ServerTimeUtil.serverTimeDiff());
    }

    @Override
    public String getModuleName() {
        return "biz.freshcode.learn.gwt.Mod1";
    }
}
