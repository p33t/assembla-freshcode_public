package biz.freshcode.learn.gwt.client.experiment.chart.gantt;

import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.HTML;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;

public class GanttDemo extends AbstractIsWidget<BorderLayoutContainer> {
    @Override
    protected BorderLayoutContainer createWidget() {
        return new BorderLayoutContainerBuilder()
                .centerWidget(new HTML("<p>Hi</p>"))
                .borderLayoutContainer;
    }
}
