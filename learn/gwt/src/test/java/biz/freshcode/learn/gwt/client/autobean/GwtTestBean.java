package biz.freshcode.learn.gwt.client.autobean;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.web.bindery.autobean.shared.AutoBean;

public class GwtTestBean extends GWTTestCase {

    public void testSmoke() {
        assertTrue(true);
    }

    public void testInstantiate() {
//        Doesn't work
//        AutoBeanFactory f = GWT.create(AutoBeanFactory.class);
//        AutoBean<TestBean> ab = f.create(TestBean.class);
        TestBean.Factory f = GWT.create(TestBean.Factory.class);
        AutoBean<TestBean> ab = f.testBean();
        assertNotNull(ab);
        TestBean b = ab.as();
        assertNotNull(b);

        assertNotNull(f.create(TestBean.class));
//        Doesn't work
//        assertNotNull(f.create(TestBean2.class));
    }

    @Override
    public String getModuleName() {
        return "biz.freshcode.learn.gwt.Mod1JUnit";
    }
}
