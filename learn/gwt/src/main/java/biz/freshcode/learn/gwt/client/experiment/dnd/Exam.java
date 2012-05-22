package biz.freshcode.learn.gwt.client.experiment.dnd;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 *
 */
public interface Exam extends Attended<Student>, Named {
    Factory FACTORY = GWT.create(Factory.class);

    //    String getRoom();
//    void setRoom(String r);
    interface Factory extends AutoBeanFactory {
        AutoBean<Exam> auto();
    }
}
