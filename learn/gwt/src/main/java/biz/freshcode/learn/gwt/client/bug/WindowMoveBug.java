package biz.freshcode.learn.gwt.client.bug;

import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widget.client.TextButton;
import com.sencha.gxt.widget.core.client.Window;

public class WindowMoveBug extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        TextButton b = new TextButton("Open Window");
        b.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Window w = new Window();
                w.setHeadingHtml("Heading");
                w.add(new HTMLPanel("<p>The Body is a little wide so it will wrap</p>"));
                w.setMinHeight(10);
                w.setMinWidth(20);
                w.setOnEsc(true);
                w.show();

            }
        });
        return new SimplePanel(b);
    }
}
