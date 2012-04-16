package biz.freshcode.learn.gwt.client.experiment.forms;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

import java.util.Date;

public interface FormBeanSub {
    Date getDt();

    void setDt(Date dt);

    String getName();

    void setName(String name);

    interface Factory extends AutoBeanFactory {
        AutoBean<FormBeanSub> create();
    }
}
