package biz.freshcode.learn.gwt.mod1.client.experiment.appearance;

import biz.freshcode.learn.gwt.mod1.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class AppearanceDemo extends AbstractIsWidget {

    @Override
    protected Widget createWidget() {
        return new HTMLPanel("<p>See Toolbar demo</p>");
    }
}
