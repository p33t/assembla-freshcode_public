package biz.freshcode.learn.gwt.client.autobean;

import biz.freshcode.learn.gwt.client.util.GenericAutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBean;

import java.util.Date;

public interface TestBean {
    String getStr();

    void setStr(String str);

    Integer getNum();

    void setNum(Integer num);

    Date getDt();

    void setDt(Date dt);


    NestedBean getNested();

    void setNested(NestedBean nb);

    public interface Factory extends GenericAutoBeanFactory<TestBean> {
        AutoBean<TestBean> auto();
    }
}
