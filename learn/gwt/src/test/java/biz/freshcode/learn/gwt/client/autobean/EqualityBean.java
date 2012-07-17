package biz.freshcode.learn.gwt.client.autobean;

import biz.freshcode.learn.gwt.client.util.GenericAutoBeanFactory;
import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

import static com.google.web.bindery.autobean.shared.AutoBeanUtils.deepEquals;
import static com.google.web.bindery.autobean.shared.AutoBeanUtils.getAutoBean;

public interface EqualityBean {
    Factory FACTORY = GWT.create(Factory.class);

    String getStr();

    void setStr(String s);

/* Nope... java.lang.Error: Unresolved compilation problems:
	Duplicate method equals(Object) in type new EqualityBean(){}
	Duplicate method hashCode() in type new EqualityBean(){}

public void testCategoryEquality() {
        EqualityBean e1 = EqualityBean.FACTORY.auto().as();
        EqualityBean e2 = EqualityBean.FACTORY.auto().as();
        assertEquals(e1, e2);
        e1.setStr("bruce");
        assertFalse(e1.equals(e2));
        e2.setStr("bruce");
        assertEquals(e1, e2);
    }

    boolean equals(Object obj);

    int hashCode();
*/


    @AutoBeanFactory.Category(Ops.class)
    interface Factory extends GenericAutoBeanFactory<EqualityBean> {
        @Override
        AutoBean<EqualityBean> auto();
    }

    static class Ops {
        public static boolean equals(AutoBean<EqualityBean> subject, Object obj) {
            if (!(obj instanceof EqualityBean)) return false;

            EqualityBean e2 = (EqualityBean) obj;
            AutoBean<EqualityBean> a2 = getAutoBean(e2);
            return deepEquals(subject, a2);
        }

        public static int hashCode(AutoBean<EqualityBean> subject) {
            return 42;
        }
    }
}
