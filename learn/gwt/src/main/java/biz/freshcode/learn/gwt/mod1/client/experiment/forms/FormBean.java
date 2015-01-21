package biz.freshcode.learn.gwt.mod1.client.experiment.forms;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

import java.util.List;
import java.util.Set;

public interface FormBean {
    Factory FACTORY = GWT.create(Factory.class);

    void setStr(String str);

    String getStr();

    void setNum(Integer num);

    Integer getNum();

    List<FormBeanChild> getChildren();

    void setChildren(List<FormBeanChild> subs);

    Set<AmPm> getPreferredTimes();

    void setPreferredTimes(Set<AmPm> s);

    public interface Factory extends AutoBeanFactory {
        AutoBean<FormBean> auto();
//        FormBean create();
    }
}
