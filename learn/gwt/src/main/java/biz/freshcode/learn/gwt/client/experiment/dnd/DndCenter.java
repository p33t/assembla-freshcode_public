package biz.freshcode.learn.gwt.client.experiment.dnd;

import biz.freshcode.learn.gwt.client.uispike.builder.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.core.client.util.Util;
import com.sencha.gxt.fx.client.FxElement;
import com.sencha.gxt.widget.core.client.box.PromptMessageBox;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.HideEvent;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * The center of the Drag and Drop example.
 */
public class DndCenter extends AbstractIsWidget<VerticalLayoutContainer> {
    private Logger log = Logger.getLogger(getClass().getName());

    @Override
    protected VerticalLayoutContainer createWidget() {
        VerticalLayoutContainer container = new VerticalLayoutContainerBuilder()
                .add(new HTMLPanel("<h2>Exams</h2><p>Drag one or more tree elements to this outer panel or to one of the exam panels.</p>"))
                .add(elem("Prog'g 101", DndUtil.STUDENTS))
                .add(elem("History 202", Util.createList(DndUtil.STUDENTS.iterator().next())))
                .styleName(Bundle.INSTANCE.style().blackBorder(), true)
                .verticalLayoutContainer;

        new DropSupport(container) {
            @Override
            protected DropAssessment dropQuery(DragData data) {
                final Set<Student> students = data.getPayload(Student.class);
                return new DropAssessment("Create exam with " + students.size() + " Student(s)", new Runnable() {
                    @Override
                    public void run() {
                        processDrop(students);
                    }
                });
            }
        };
        return container;
    }

    private void processDrop(final Set<Student> attendees) {
        if (attendees.isEmpty()) {
            log.warning("No students");
            return;
        }
        final PromptMessageBox box = new PromptMessageBox("New Exam", "Exam name:");
        box.addHideHandler(new HideEvent.HideHandler() {
            @Override
            public void onHide(HideEvent event) {
                String name = box.getValue();
                // null when 'cancelled'
                if (name == null || name.isEmpty()) return;
                IsWidget examWidget = elem(name, Collections.unmodifiableSet(attendees));
                asWidget().add(examWidget);
                examWidget.asWidget().getElement().<FxElement>cast().blink();
            }
        });
        box.show();
    }

    private IsWidget elem(String name, List<Student> attendees) {
        Set<Student> s = new HashSet<Student>(attendees);
        return elem(name, Collections.unmodifiableSet(s));
    }

    private IsWidget elem(String name, Set<Student> attendees) {
        Exam e = Exam.FACTORY.auto().as();
        e.setName(name);
        e.setId("Exam " + System.identityHashCode(e));
        e.setAttendees(attendees);
        return new ExamWidget(e);
    }
}
