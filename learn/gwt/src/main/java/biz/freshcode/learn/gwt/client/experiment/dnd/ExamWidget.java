package biz.freshcode.learn.gwt.client.experiment.dnd;

import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.sencha.gxt.dnd.core.client.DndDragEnterEvent;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.DropTarget;
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
        DropTarget target = new DropTarget(container);
        target.setOverStyle(Bundle.INSTANCE.style().dragOver());
        target.addDropHandler(new DndDropEvent.DndDropHandler() {
            @Override
            public void onDrop(DndDropEvent event) {
                Object data = event.getData();
                processDrop(data);
            }
        });
        target.addDragEnterHandler(new DndDragEnterEvent.DndDragEnterHandler() {
            @Override
            public void onDragEnter(DndDragEnterEvent event) {
                // NOTE: Changing status text is permanent for the entire drag operation.
                // Do not allow if all students are already present.
                Set<Student> students = DndUtil.droppedStudents(event.getDragSource().getData());
                if (exam.getAttendees().containsAll(students)) event.getStatusProxy().setStatus(false);
            }
        });
        return container;
    }

    private void processDrop(Object data) {
        Set<Student> students = DndUtil.droppedStudents(data);
        if (students.isEmpty()) log.warning("Nothing to process from data " + data);
        else {
            students.addAll(exam.getAttendees());
            Set<Student> attendees = new HashSet<Student>(students);
            exam.setAttendees(Collections.unmodifiableSet(attendees));
            asWidget().setWidget(renderPanel());
        }
    }

    private HTMLPanel renderPanel() {
        String html = "<p>" + exam.getName() + " (" + exam.getAttendees().size() + ")</p>";
        return new HTMLPanel(html);
    }

}
