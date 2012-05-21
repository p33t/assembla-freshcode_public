package biz.freshcode.learn.gwt.client.experiment.dnd;

import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Util;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.tree.Tree;

public class StudentMasterPanel extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        TreeStore<Named> ts = new TreeStore<Named>(Named.ACCESS.id());
        ts.add(Util.createList((Named) course("Business"), course("Engineering")));

        return new Tree(ts, Named.ACCESS.name());
    }

    private Course course(String name) {
        Course c = Course.FACTORY.auto().as();
        c.setName(name);
        c.setId("Course " + System.identityHashCode(c));
        return c;
    }
}
