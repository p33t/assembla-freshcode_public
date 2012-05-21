package biz.freshcode.learn.gwt.client.experiment.dnd;

import com.sencha.gxt.core.client.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DndUtil {
    public static final List<Course> COURSES = new ArrayList<Course>(Util.createList(
            course("Business", "Tom", "Dick", "Harry"),
            course("Engineering", "Betty", "Selma", "Pebbles")
    ));
    public static final List<Student> STUDENTS = new ArrayList<Student>();

    static {
        for (Course c: COURSES) STUDENTS.addAll(c.getAttendees());
    }

//    public static <T> List<T> iterableToUnmodList(Iterable<T> it) {
//        ArrayList<T> l = new ArrayList<T>();
//        for (T s : it) l.add(s);
//        return Collections.unmodifiableList(l);
//    }

    public static Course course(String name, List<Student> ss) {
        Course c = Course.FACTORY.auto().as();
        c.setAttendees(ss);
        c.setName(name);
        c.setId("Course " + System.identityHashCode(c));
        return c;
    }

    private static Course course(String name, String... students) {
        List<Student> ss = new ArrayList<Student>();
        for (String s : students) ss.add(student(s));
        return course(name, ss);
    }

    private static Student student(String name) {
        Student s = Student.FACTORY.auto().as();
        s.setName(name);
        s.setId("Student " + System.identityHashCode(s));
        return s;
    }

}
