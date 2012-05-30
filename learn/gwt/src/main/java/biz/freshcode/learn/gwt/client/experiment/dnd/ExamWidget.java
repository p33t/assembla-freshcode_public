package biz.freshcode.learn.gwt.client.experiment.dnd;

import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.sencha.gxt.fx.client.FxElement;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 *
 */
public class ExamWidget extends AbstractIsWidget<SimpleContainer> {
    private Logger log = Logger.getLogger(getClass().getName());
    private final Exam exam;

    public ExamWidget(Exam exam) {
        this.exam = exam;
    }

    @Override
    protected SimpleContainer createWidget() {
        final SimpleContainer container = new SimpleContainer();
        container.add(renderPanel());
        container.setStyleName(Bundle.INSTANCE.style().dropElem(), true);

        new DropSupport(container) {

            @Override
            protected DragData.DropOp dropOpOrNull(DragData data) {
                final Set<Student> students = data.getPayload(Student.class);
                if (exam.getAttendees().containsAll(students)) return null;
                return new DragData.DropOp() {
                    @Override
                    public String getHoverMessage() {
                        HashSet<Student> toAdd = new HashSet<Student>();
                        toAdd.addAll(students);
                        toAdd.removeAll(exam.getAttendees());
                        return "Add " + toAdd.size() + " Student(s)";
                    }

                    @Override
                    public void run() {
                        processDropStudents(students);
                    }
                };
            }
        };
        return container;
    }

    private void processDropStudents(Set<Student> students) {
        students.addAll(exam.getAttendees());
        Set<Student> attendees = new HashSet<Student>(students);
        exam.setAttendees(Collections.unmodifiableSet(attendees));
        SimpleContainer sc = asWidget();
        sc.setWidget(renderPanel());
        sc.getElement().<FxElement>cast().blink();
    }

    private HTMLPanel renderPanel() {
        String html = "<p>" + exam.getName() + " (" + exam.getAttendees().size() + ")</p>";
        return new HTMLPanel(html);
    }

}
