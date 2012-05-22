package biz.freshcode.learn.gwt.client.experiment.dnd;

import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public class ExamWidget extends AbstractIsWidget {

    private final DndCenter host;
    private final Exam exam;

    public ExamWidget(DndCenter host, Exam exam) {
        this.host = host;
        this.exam = exam;
    }

    @Override
    protected Widget createWidget() {
        String html = "<p>" + exam.getName() + " (" + exam.getAttendees().size() + ")</p>";
        HTMLPanel p = new HTMLPanel(html);
        p.setStyleName(Bundle.INSTANCE.style().dropElem(), true);
        return p;
    }
}
