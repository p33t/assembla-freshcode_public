package biz.freshcode.learn.gwt.mod1.client.experiment.dnd;

import biz.freshcode.learn.gwt.mod1.client.util.GenericAutoBeanFactory;
import com.google.gwt.core.client.GWT;

/**
 *
 */
public interface Exam extends Attended<Student>, Named {
    Factory FACTORY = GWT.create(Factory.class);

    //    String getRoom();
//    void setRoom(String r);
    interface Factory extends GenericAutoBeanFactory<Exam> {
    }
}
