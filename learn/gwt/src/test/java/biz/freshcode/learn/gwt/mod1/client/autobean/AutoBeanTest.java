package biz.freshcode.learn.gwt.mod1.client.autobean;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import org.junit.Test;

import static com.google.web.bindery.autobean.shared.AutoBeanUtils.getAutoBean;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class AutoBeanTest {
    TestBean.Factory factTest = AutoBeanFactorySource.create(TestBean.Factory.class);
    NestedBean.Factory factNested = AutoBeanFactorySource.create(NestedBean.Factory.class);

    @Test
    public void confirmCanonicalEqualsDoesNOTWork() {
        TestBean t1 = testBean("s1");
        TestBean t2 = testBean("s1");
        // BAD
        assertFalse(t1.equals(t2));
    }

    @Test
    public void confirmUtilsEqualsWorksUnNested() {
        TestBean t1 = testBean("s1");
        TestBean t2 = testBean("s1");
        assertTrue(deepEquals(t1, t2));
        t2.setStr("s2");
        assertFalse(deepEquals(t1, t2));
    }

    @Test
    public void confirmUtilsEqualsWorks() {
        TestBean t1 = testBean("s1");
        TestBean t2 = testBean("s1");
        t1.setNested(nested("n1"));
        t2.setNested(nested("n1"));
        assertTrue(deepEquals(t1, t2));
        t2.getNested().setNest("n2");
        assertFalse(deepEquals(t1, t2));
    }

    @Test
    public void confirmTagsAreBenign() {
        TestBean t1 = testBean("s1");
        TestBean t2 = testBean("s1");
        getAutoBean(t1).setTag("x", 99);
        getAutoBean(t2).setTag("x", 1);
        assertTrue(deepEquals(t1, t2));
    }

    private TestBean testBean(String str) {
        TestBean tb2 = testBean();
        tb2.setStr(str);
        return tb2;
    }

    private NestedBean nested(String str) {
        NestedBean n = factNested.auto().as();
        n.setNest(str);
        return n;
    }

    private boolean deepEquals(TestBean t1, TestBean t2) {
        AutoBean<TestBean> a1 = getAutoBean(t1);
        AutoBean<TestBean> a2 = getAutoBean(t2);
        return AutoBeanUtils.deepEquals(a1, a2);
    }

    private TestBean testBean() {
        AutoBean<TestBean> auto = factTest.auto();
        return auto.as();
    }
}
