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


    NestedBean getNested();

    void setNested(NestedBean nb);

    // NOTE: Extending GenericAutoBeanFactory breaks AutoBeanFactorySource (and any unit testing)
    public interface Factory extends AutoBeanFactory {
        AutoBean<TestBean> auto();
    }
}
