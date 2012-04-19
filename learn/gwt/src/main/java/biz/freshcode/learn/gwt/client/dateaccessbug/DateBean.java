package biz.freshcode.learn.gwt.client.dateaccessbug;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

import java.util.Date;

public interface DateBean {
    Factory FACTORY = GWT.create(Factory.class);

    Integer getId();
    void setId(Integer id);

    void setDt(Date dt);
    Date getDt();

    interface Factory extends AutoBeanFactory {
        AutoBean<DateBean> auto();
    }
}
