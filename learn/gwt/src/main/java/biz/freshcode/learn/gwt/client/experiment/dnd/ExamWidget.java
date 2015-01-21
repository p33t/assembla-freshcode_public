package biz.freshcode.learn.gwt.client.experiment.dnd;

import biz.freshcode.learn.gwt.client.experiment.dnd.dragdata.DragData;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.sencha.gxt.fx.client.FxElement;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.newSetFrom;

public class ExamWidget extends AbstractIsWidget<SimpleContainer> {
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
            protected DropAssessment dropQuery(DragData data) {
                if (data.hasPayload(Student.class)) {
                    Set<Student> toAdd = newSetFrom(data.getPayload(Student.class));
                    toAdd.removeAll(exam.getAttendees());
                    if (!toAdd.isEmpty()) {
                        // In reality, just add this to the list of possible actions to take.
                        return new DropAssessment("Add " + toAdd.size() + " Student(s)", new AddStudents(toAdd));
                    } else if (data.hasExclusivePayload(Student.class)) {
                        return new DropAssessment("All students already present");
                    }
                }
                // To illustrate this option, drag a course who's members are all already present on an exam
                return DropAssessment.NOT_HANDLED;
            }
        };
        return container;
    }

    private void processDropStudents(Set<Student> toAdd) {
        Set<Student> attendees = new HashSet<Student>(toAdd);
        attendees.addAll(exam.getAttendees());
        exam.setAttendees(Collections.unmodifiableSet(attendees));
        SimpleContainer sc = asWidget();
        sc.setWidget(renderPanel());
        sc.getElement().<FxElement>cast().blink();
    }

    private HTMLPanel renderPanel() {
        String html = "<p>" + exam.getName() + " (" + exam.getAttendees().size() + ")</p>";
        return new HTMLPanel(html);
    }

    private class AddStudents implements Runnable {
        private final Set<Student> toAdd;

        public AddStudents(Set<Student> toAdd) {
            this.toAdd = toAdd;
        }

        @Override
        public void run() {
            processDropStudents(toAdd);
        }
    }
}
