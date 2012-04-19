package biz.freshcode.learn.gwt.client.dateaccessbug;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

import java.util.List;

public interface ParentBean {
    Factory FACTORY = GWT.create(Factory.class);

    void setDates(List<DateBean> dates);

    List<DateBean> getDates();

    interface Factory extends AutoBeanFactory {
        AutoBean<ParentBean> auto();
    }
}
