package biz.freshcode.learn.gwt.client.experiment.forms;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

import java.util.List;

public interface FormBean {
    void setStr(String str);

    String getStr();

    void setNum(Integer num);

    Integer getNum();

    List<FormBeanSub> getSubs();

    void setSubs(List<FormBeanSub> subs);

    public interface Factory extends AutoBeanFactory {
        AutoBean<FormBean> create();
    }
}
