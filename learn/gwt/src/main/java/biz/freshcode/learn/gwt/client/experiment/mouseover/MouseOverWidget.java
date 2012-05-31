package biz.freshcode.learn.gwt.client.experiment.mouseover;

import biz.freshcode.learn.gwt.client.uispike.builder.HTMLPanelBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class MouseOverWidget extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        HTMLPanel p = new HTMLPanelBuilder(new HTMLPanel("<p>Mouse over me!</p>"))
                .styleName(Bundle.INSTANCE.style().blackBorder(), true)
                .hTMLPanel;
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
