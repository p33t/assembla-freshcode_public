package biz.freshcode.learn.gwt.client.experiment.forms;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

public interface PeriodBean {
    Factory FACTORY = GWT.create(Factory.class);

    void setStart(Long t);

    Long getStart();

    void setDuration(Long d);

    Long getDuration();

    interface Factory extends AutoBeanFactory {
        AutoBean<PeriodBean> auto();
    }
}
