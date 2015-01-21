package biz.freshcode.learn.gwt.mod1.client.experiment.celltable;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

import java.util.Date;

public interface Bean {
    String getStr();
    void setStr(String str);
    Integer getNum();
    void setNum(Integer num);
    Date getDt();
    void setDt(Date dt);

    interface Factory extends AutoBeanFactory {
        AutoBean<Bean> create();
    }
}
