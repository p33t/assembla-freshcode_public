package biz.freshcode.learn.gwt.client.experiment.mvp.homebake;

import biz.freshcode.learn.gwt.client.uispike.builder.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;

public class OuterWidget extends AbstractIsWidget<BorderLayoutContainer> {
    @Override
    protected BorderLayoutContainer createWidget() {
        return new BorderLayoutContainerBuilder()
                .centerWidget(new HTMLPanel("<p>Home Bake</p>"))
                .borderLayoutContainer;
    }
}
