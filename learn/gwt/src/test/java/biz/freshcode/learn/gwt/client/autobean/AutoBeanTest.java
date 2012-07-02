package biz.freshcode.learn.gwt.client.autobean;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class AutoBeanTest {
    TestBean.Factory factory = AutoBeanFactorySource.create(TestBean.Factory.class);
    
    @Test
    public void confirmCanonicalEqualsDoesNotWork() {
        TestBean tb = testBean("s1");
        TestBean tb2 = testBean("s1");
        assertFalse(tb.equals(tb2));
    }

    @Test
    public void confirmUtilsEqualsWorks() {
        TestBean tb = testBean("s1");
        TestBean tb2 = testBean("s1");
        assertTrue(deepEquals(tb, tb2));
        tb2.setStr("s2");
        assertFalse(deepEquals(tb, tb2));
    }

    private TestBean testBean(String str) {
        TestBean tb2 = testBean();
        tb2.setStr(str);
        return tb2;
    }

    private boolean deepEquals(TestBean t1, TestBean t2) {
        AutoBean<TestBean> a1 = AutoBeanUtils.getAutoBean(t1);
        AutoBean<TestBean> a2 = AutoBeanUtils.getAutoBean(t2);
        return AutoBeanUtils.deepEquals(a1, a2);
    }

    private TestBean testBean() {
        AutoBean<TestBean> auto = factory.auto();
        return auto.as();
    }
}
