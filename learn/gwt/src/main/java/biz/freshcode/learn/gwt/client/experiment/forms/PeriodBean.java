package biz.freshcode.learn.gwt.client.experiment.forms;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * Bean that requires elaborate Ui elements.
 * HrMin is just a simple case but Start/Duration are meant to be edited simultaneously.
 */
public interface PeriodBean {
    Factory FACTORY = GWT.create(Factory.class);

    void setHrMin(Long t);

    Long getHrMin();

    void setHrMin2(Long t);

    Long getHrMin2();

    void setStart(Long t);

    Long getStart();

    void setDuration(Long d);

    Long getDuration();

    interface Factory extends AutoBeanFactory {
        AutoBean<PeriodBean> auto();
    }
}
