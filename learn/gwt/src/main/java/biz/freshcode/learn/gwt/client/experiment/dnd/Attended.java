package biz.freshcode.learn.gwt.client.experiment.dnd;

import java.util.Set;

/**
 *
 */
public interface Attended<T> {
    Set<T> getAttendees();

    void setAttendees(Set<T> l);
}
