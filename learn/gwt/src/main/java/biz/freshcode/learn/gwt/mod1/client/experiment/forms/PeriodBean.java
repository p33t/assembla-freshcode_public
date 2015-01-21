package biz.freshcode.learn.gwt.mod1.client.experiment.forms;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * Bean that requires elaborate Ui elements.
 * HrMin is just a simple case but Start/Duration are meant to be edited simultaneously.
 */
public interface PeriodBean extends StartDuration {
    Factory FACTORY = GWT.create(Factory.class);

    void setHrMin1(Long t);

    Long getHrMin1();

    void setHrMin2(Long t);

    Long getHrMin2();

    void setStart(Long t);

    void setDuration(Long d);

    interface Factory extends AutoBeanFactory {
        AutoBean<PeriodBean> auto();
    }
}
