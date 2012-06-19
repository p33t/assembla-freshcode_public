package biz.freshcode.learn.gwt.client.experiment.requestfactory;

import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class RequestFactoryDemo extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        return new HTMLPanel("<p>Hello</p>");
    }
}
