package biz.freshcode.learn.gwt.client.experiment.dnd;

import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.TreeDragSource;
import com.sencha.gxt.widget.core.client.tree.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class StudentMasterPanel extends AbstractIsWidget {
    private Logger log = Logger.getLogger(getClass().getName());

    @Override
    protected Widget createWidget() {
        // Facilitates adding the same object multiple times in a tree.  We use a wrapper ref object.
        ModelKeyProvider<Ref<Named>> instanceUniqueId = new ModelKeyProvider<Ref<Named>>() {
            @Override
            public String getKey(Ref<Named> item) {
                return "ref " + System.identityHashCode(item);
            }
        };

        TreeStore<Ref<Named>> ts = new TreeStore<Ref<Named>>(instanceUniqueId);

        List<Course> courses = DndUtil.COURSES;
        List<Student> students = DndUtil.STUDENTS;

        List<CourseAdapter> ads = new ArrayList<CourseAdapter>();
        for (Course c: courses) ads.add(new CourseAdapter(c));
        ads.add(new CourseAdapter(course("All Courses", students)));

        ts.addSubTree(0, ads);

        Tree tree = new Tree(ts, DndUtil.valueProvider(Named.ACCESS.name()));

        new TreeDragSource(tree) {
            @Override
            protected void onDragDrop(DndDropEvent event) {
                // don't do anything (like remove elements)
                log.info("onDragDrop " + event.getData() + " " + event.getTarget().getClass().getName());
            }
        };

        return tree;
    }

    private Course course(String name, List<Student> ss) {
        return DndUtil.course(name, ss);
    }

    /**
     * Adapts a course for display in a tree.
     */
    public static class CourseAdapter implements TreeStore.TreeNode<Ref<Named>> {
        private final Course course;
        private final Ref<Named> ref;

        public CourseAdapter(Course course) {
            this.course = course;
            ref = DndUtil.ref((Named) course);
        }

        @Override
        public List<? extends TreeStore.TreeNode<Ref<Named>>> getChildren() {
            List<TreeStore.TreeNode<Ref<Named>>> l = new ArrayList<TreeStore.TreeNode<Ref<Named>>>();
            for (Student s : course.getAttendees()) {
                l.add(new StudentAdapter(s));
            }
            return l;
        }

        @Override
        public Ref<Named> getData() {
            return ref;
        }
    }

    /**
     * Adapts a Student for display in a tree.
     */
    public static class StudentAdapter implements TreeStore.TreeNode<Ref<Named>> {
        private final Student student;
        private final Ref<Named> ref;

        public StudentAdapter(Student student) {
            this.student = student;
            ref = DndUtil.ref((Named) student);
        }

        @Override
        public List<? extends TreeStore.TreeNode<Ref<Named>>> getChildren() {
            return Collections.EMPTY_LIST;
        }

        @Override
        public Ref<Named> getData() {
            return ref;
        }
    }
}
