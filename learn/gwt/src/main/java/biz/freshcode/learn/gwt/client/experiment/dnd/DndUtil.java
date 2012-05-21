package biz.freshcode.learn.gwt.client.experiment.dnd;

import com.sencha.gxt.core.client.ValueProvider;
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

    /**
     * Creates an adapter for a value provider so it can see past a 'Ref'.
     */
    public static <T, U> ValueProvider<Ref<T>, U> valueProvider(final ValueProvider<T, U> delegate) {
        return new ValueProvider<Ref<T>, U>() {
            @Override
            public U getValue(Ref<T> object) {
                return delegate.getValue(object.getObj());
            }

            @Override
            public void setValue(Ref<T> object, U value) {
                delegate.setValue(object.getObj(), value);
            }

            @Override
            public String getPath() {
                return delegate.getPath();
            }
        };
    }

    public static <T, U extends T> Ref<T> ref(U obj) {
        Ref<T> r = Ref.FACTORY.<T>auto().as();
        r.setObj(obj);
        return r;
    }

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
