package biz.freshcode.learn.gwt.client.autobean;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.web.bindery.autobean.shared.AutoBean;

import static com.google.web.bindery.autobean.shared.AutoBeanUtils.deepEquals;

public class GwtTestBean extends GWTTestCase {

    public void testSmoke() {
        assertTrue(true);
    }

    public void testInstantiate() {
//        Doesn't work
//        AutoBeanFactory f = GWT.create(AutoBeanFactory.class);
//        AutoBean<TestBean> ab = f.create(TestBean.class);
        TestBean.Factory f = GWT.create(TestBean.Factory.class);
        AutoBean<TestBean> ab = f.auto();
        assertNotNull(ab);
        TestBean b = ab.as();
        assertNotNull(b);

        assertNotNull(f.create(TestBean.class));
//        Doesn't work
//        assertNotNull(f.create(TestBean2.class));
    }

    public void testEquality() {
        TestBean.Factory f = GWT.create(TestBean.Factory.class);
        AutoBean<TestBean> a1 = f.auto();
        TestBean b1 = a1.as();
        AutoBean<TestBean> a2 = f.auto();
        TestBean b2 = a2.as();
// Nope...        assertEquals(b1, b2);
        assertTrue(deepEquals(a1, a2));
        b1.setStr("bruce");
        assertFalse(deepEquals(a1, a2));
        b2.setStr("bruce");
        assertTrue(deepEquals(a1, a2));
    }

    @Override
    public String getModuleName() {
        return "biz.freshcode.learn.gwt.Mod1JUnit";
    }
}
