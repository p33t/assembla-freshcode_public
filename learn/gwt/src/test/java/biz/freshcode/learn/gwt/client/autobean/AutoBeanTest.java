package biz.freshcode.learn.gwt.client.autobean;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;

public class AutoBeanTest {
    TestBean.Factory factory = AutoBeanFactorySource.create(TestBean.Factory.class);
    
    @Test
    public void confirmCanonicalEqualityDoesNotWork() {
        TestBean tb = testBean();
        tb.setStr("s1");
        TestBean tb2 = testBean();
        tb2.setStr("s1");
        assertFalse(tb.equals(tb2));
    }

    private TestBean testBean() {
        AutoBean<TestBean> auto = factory.auto();
        return auto.as();
    }
}
