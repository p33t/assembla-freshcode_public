package biz.freshcode.learn.gwt.client.experiment.dnd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class Exam {
    public final List<Student> attendees;
    public Exam(Iterable<Student> attendees) {
        ArrayList<Student> l = new ArrayList<Student>();
        for (Student s: attendees) l.add(s);
        this.attendees = Collections.unmodifiableList(l);
    }
}
