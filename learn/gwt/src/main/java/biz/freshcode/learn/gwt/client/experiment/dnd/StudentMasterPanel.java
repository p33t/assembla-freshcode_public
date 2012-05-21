package biz.freshcode.learn.gwt.client.experiment.dnd;

import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Util;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.tree.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentMasterPanel extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        TreeStore<Named> ts = new TreeStore<Named>(Named.ACCESS.id());

        List<Course> courses = Util.createList(
                course("Business", "Tom", "Dick", "Harry"),
                course("Engineering", "Betty", "Selma", "Pebbles")
        );

        List<CourseAdapter> ads = new ArrayList<CourseAdapter>();
        for (Course c: courses) ads.add(new CourseAdapter(c));

        ts.addSubTree(0, ads);

        return new Tree(ts, Named.ACCESS.name());
    }

    private Course course(String name, String... students) {
        Course c = Course.FACTORY.auto().as();
        c.setName(name);
        c.setId("Course " + System.identityHashCode(c));
        List<Student> ss = new ArrayList<Student>();
        for (String s : students) ss.add(student(s));
        c.setAttendees(ss);
        return c;
    }

    private Student student(String name) {
        Student s = Student.FACTORY.auto().as();
        s.setName(name);
        s.setId("Student " + System.identityHashCode(s));
        return s;
    }

    public static class CourseAdapter implements TreeStore.TreeNode<Named> {
        private final Course course;

        public CourseAdapter(Course course) {
            this.course = course;
        }

        @Override
        public List<? extends TreeStore.TreeNode<Named>> getChildren() {
            List<TreeStore.TreeNode<Named>> l = new ArrayList<TreeStore.TreeNode<Named>>();
            for (Student s : course.getAttendees()) {
                l.add(new StudentAdapter(s));
            }
            return l;
        }

        @Override
        public Named getData() {
            return course;
        }
    }

    public static class StudentAdapter implements TreeStore.TreeNode<Named> {
        private final Student student;

        public StudentAdapter(Student student) {
            this.student = student;
        }

        @Override
        public List<? extends TreeStore.TreeNode<Named>> getChildren() {
            return Collections.EMPTY_LIST;
        }

        @Override
        public Named getData() {
            return student;
        }
    }
}
