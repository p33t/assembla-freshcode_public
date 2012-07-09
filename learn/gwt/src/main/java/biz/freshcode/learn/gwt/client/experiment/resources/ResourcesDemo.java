package biz.freshcode.learn.gwt.client.experiment.resources;

import biz.freshcode.learn.gwt.client.uispike.builder.HTMLPanelBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.Widget;

import static biz.freshcode.learn.gwt.client.experiment.resources.Bundle.STYLE;

public class ResourcesDemo extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        return new VerticalLayoutContainerBuilder()
                .add(new HTMLPanelBuilder("<p>Style Example</p>")
                        .addStyleName(STYLE.highlight())
                        .hTMLPanel)
                .verticalLayoutContainer;
    }
}
