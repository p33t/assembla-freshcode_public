package biz.freshcode.learn.gwt.client.autobean;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

import java.util.Date;

public interface TestBean {
    String getStr();
    void setStr(String str);
    Integer getNum();
    void setNum(Integer num);
    Date getDt();
    void setDt(Date dt);
    
    public interface Factory extends AutoBeanFactory {
        AutoBean<TestBean> testBean();
    }
}
