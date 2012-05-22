package biz.freshcode.learn.gwt.client.experiment.dnd;

import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.DropTarget;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

import java.util.*;
import java.util.logging.Logger;

/**
 *
 */
public class ExamWidget extends AbstractIsWidget {
    private Logger log = Logger.getLogger(getClass().getName());
    private final Exam exam;

    public ExamWidget(Exam exam) {
        this.exam = exam;
    }

    @Override
    protected Widget createWidget() {
        final SimpleContainer container = new SimpleContainer();
        container.add(renderPanel());
        container.setStyleName(Bundle.INSTANCE.style().dropElem(), true);
        DropTarget target = new DropTarget(container);
        target.setOverStyle(Bundle.INSTANCE.style().dragOver());
        target.addDropHandler(new DndDropEvent.DndDropHandler() {
            @Override
            public void onDrop(DndDropEvent event) {
                Object data = event.getData();
                boolean processed = false;
                if (data instanceof List) {
                    List l = (List) data;
                    Set<Student> students = new HashSet<Student>();
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
                    if (!students.isEmpty()) {
                        processed = true;
                        students.addAll(exam.getAttendees());
                        ArrayList<Student> attendees = new ArrayList<Student>(students);
                        exam.setAttendees(Collections.unmodifiableList(attendees));
                        container.setWidget(renderPanel());
                    }


                }
                if (!processed) log.warning("Nothing to process from " + event);
            }
        });
        return container;
    }

    private HTMLPanel renderPanel() {
        String html = "<p>" + exam.getName() + " (" + exam.getAttendees().size() + ")</p>";
        return new HTMLPanel(html);
    }

}
