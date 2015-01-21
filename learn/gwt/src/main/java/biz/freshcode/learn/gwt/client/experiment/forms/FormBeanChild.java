package biz.freshcode.learn.gwt.client.experiment.forms;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

import java.util.Date;

public interface FormBeanChild extends StartDuration {
    Factory FACTORY = GWT.create(Factory.class);

    Integer getKey();

    void setKey(Integer key);

    Date getDt();

    void setDt(Date dt);

    String getName();

    void setName(String name);

    void setStart(Long t);

    void setDuration(Long d);

    interface Factory extends AutoBeanFactory {
        AutoBean<FormBeanChild> auto();
//        FormBeanChild create();
    }
}
