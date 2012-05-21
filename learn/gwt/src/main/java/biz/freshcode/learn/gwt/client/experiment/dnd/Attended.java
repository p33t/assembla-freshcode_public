package biz.freshcode.learn.gwt.client.experiment.dnd;

import java.util.List;

/**
 *
 */
public interface Attended<T> {
    List<T> getAttendees();
    void setAttendees(List<T> l);
}
