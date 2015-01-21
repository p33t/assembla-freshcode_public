package biz.freshcode.learn.gwt.client.autobean;

import biz.freshcode.learn.gwt.client.util.GenericAutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBean;

public interface NestedBean {
    String getNest();

    void setNest(String s);

    public interface Factory extends GenericAutoBeanFactory<NestedBean> {
        AutoBean<NestedBean> auto();
    }
}
