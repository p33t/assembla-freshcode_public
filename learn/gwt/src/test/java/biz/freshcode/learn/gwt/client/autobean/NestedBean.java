package biz.freshcode.learn.gwt.client.autobean;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

public interface NestedBean {
    String getNest();

    void setNest(String s);

    public interface Factory extends AutoBeanFactory {
        AutoBean<NestedBean> auto();
    }
}
