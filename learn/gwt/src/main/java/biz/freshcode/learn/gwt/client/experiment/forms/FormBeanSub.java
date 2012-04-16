package biz.freshcode.learn.gwt.client.experiment.forms;

import java.util.Date;

public class FormBeanSub {
    private Date dt;
    private String name;

    // TODO: Try with AutoBean?
//    Date getDt();
//    void setDt(Date dt);
//
//    interface Factory extends AutoBeanFactory {
//        AutoBean<FormBeanSub> create();
//    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
