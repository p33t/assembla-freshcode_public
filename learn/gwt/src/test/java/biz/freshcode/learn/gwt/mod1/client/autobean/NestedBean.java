package biz.freshcode.learn.gwt.mod1.client.autobean;

import biz.freshcode.learn.gwt.mod1.client.util.GenericAutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBean;

public interface NestedBean {
    String getNest();

    void setNest(String s);

    public interface Factory extends GenericAutoBeanFactory<NestedBean> {
        AutoBean<NestedBean> auto();
    }
}
