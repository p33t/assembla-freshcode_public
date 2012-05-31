package biz.freshcode.learn.gwt.client.experiment.mouseover;

import biz.freshcode.learn.gwt.client.uispike.builder.HTMLPanelBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.container.CssFloatLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.CssFloatLayoutContainer;

public class MouseOverWidget extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        HTMLPanel p = new HTMLPanelBuilder(new HTMLPanel("<p>Mouse over me!</p>"))
                .styleName(Bundle.INSTANCE.style().blackBorder(), true)
                .hTMLPanel;
        // Trying to get icons to appear that can be dragged or clicked
        CssFloatLayoutContainer floater = new CssFloatLayoutContainerBuilder()
                .add(new HTMLPanel("<p>Float 1</p>"))
                .add(new HTMLPanel("<p>Float 2</p>"))
                .cssFloatLayoutContainer;
        p.add(floater);

        p.addDomHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                GWT.log("Mouse over");
            }
        }, MouseOverEvent.getType());
        p.addDomHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                GWT.log("Mouse out");
            }
        }, MouseOutEvent.getType());
        return p;
    }
}
