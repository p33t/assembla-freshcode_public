package biz.freshcode.learn.gwt.client.experiment.dnd;

import biz.freshcode.learn.gwt.client.uispike.builder.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Util;

import java.util.List;

/**
 *
 */
public class DndCenter extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        return new VerticalLayoutContainerBuilder()
                .add(new HTMLPanel("<h2>Exams</h2>"))
                .add(elem("Prog'g 101", DndUtil.STUDENTS))
                .add(elem("History 202", Util.createList(DndUtil.STUDENTS.get(0))))
                .verticalLayoutContainer;
    }

    private IsWidget elem(String name, List<Student> attendees) {
        Exam e = Exam.FACTORY.auto().as();
        e.setName(name);
        e.setId("Exam " + System.identityHashCode(e));
        e.setAttendees(attendees);
        return new ExamWidget(this, e);
    }
}
