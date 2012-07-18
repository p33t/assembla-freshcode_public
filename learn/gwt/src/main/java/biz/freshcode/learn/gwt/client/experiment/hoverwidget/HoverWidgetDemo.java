package biz.freshcode.learn.gwt.client.experiment.hoverwidget;

import biz.freshcode.learn.gwt.client.experiment.hoverwidget.reuse.HoverWidgetSupport;
import biz.freshcode.learn.gwt.client.experiment.mouseover.MouseOverState;
import biz.freshcode.learn.gwt.client.uispike.builder.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt.client.util.DummySelectHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;

public class HoverWidgetDemo extends AbstractIsWidget {
    private HoverWidgetSupport<ToolButton> hoverSupp;

    @Override
    protected Widget createWidget() {
        ToolButton hoverWidget = new ToolButton(ToolButton.SAVE, new DummySelectHandler("Saved"));
        final HtmlLayoutContainer host = new HtmlLayoutContainer("<p>Hover Over This</p>");

        hoverSupp = new HoverWidgetSupport<ToolButton>(hoverWidget);

        new MouseOverState(host, new MouseOverState.Callback() {
            @Override
            public void stateChange(MouseOverState mos) {
                if (mos.isDraggingOver()) hoverSupp.disablePopup();
                else if (mos.isOver()) hoverSupp.enablePopup(host.getAbsoluteLeft(), host.getAbsoluteTop());
                else hoverSupp.disablePopup();
            }
        });

        return new VerticalLayoutContainerBuilder()
                .add(host)
                .verticalLayoutContainer;
    }
}
