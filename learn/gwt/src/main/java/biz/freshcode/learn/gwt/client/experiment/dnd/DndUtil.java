package biz.freshcode.learn.gwt.client.experiment.dnd;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Util;
import com.sencha.gxt.data.shared.TreeStore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class DndUtil {
    public static final List<Course> COURSES = new ArrayList<Course>(Util.createList(
            course("Business", "Tom", "Dick", "Harry"),
            course("Engineering", "Betty", "Selma", "Pebbles")
    ));
    public static final Set<Student> STUDENTS = new HashSet<Student>();

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

    public static Course course(String name, Set<Student> ss) {
        Course c = Course.FACTORY.auto().as();
        c.setAttendees(ss);
        c.setName(name);
        c.setId("Course " + System.identityHashCode(c));
        return c;
    }

    public static Set<Student> droppedStudents(Object data) {
        if (data instanceof Set) return (Set<Student>) data;
        Set<Student> students = new HashSet<Student>();
        if (data instanceof List) {
            List l = (List) data;
            for (Object elem : l) {
                if (elem instanceof TreeStore.TreeNode) {
                    Object nodeData = ((TreeStore.TreeNode) elem).getData();
                    if (nodeData instanceof Ref) {
                        Object payload = ((Ref) nodeData).getObj();
                        if (payload instanceof Student) students.add((Student) payload);
                        else if (payload instanceof Course) students.addAll(((Course) payload).getAttendees());
                    }
                }
            }
        }
        return students;
    }

    private static Course course(String name, String... students) {
        Set<Student> ss = new HashSet<Student>();
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
