package biz.freshcode.learn.gwt.client.bug;

import biz.freshcode.learn.gwt2.common.client.util.AbstractIsWidget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class WindowMoveBug extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        return new LaunchPanel().asWidget();
    }

    /**
     * An entry point for stand alone execution.
     */
    public static class Entry implements EntryPoint {
        @Override
        public void onModuleLoad() {
            Viewport v = new Viewport();
            v.setWidget(new LaunchPanel());
            RootLayoutPanel.get().add(v);
        }
    }

    public static class LaunchPanel implements IsWidget {
        private final Widget w;

        public LaunchPanel() {
            TextButton b = new TextButton("Open Window");
            b.addSelectHandler(new SelectEvent.SelectHandler() {
                @Override
                public void onSelect(SelectEvent event) {
                    Window w = new Window();
                    w.setHeading("A Window");
                    w.add(new HTMLPanel("<p>Move this window down and to the right.<br/>" +
                            "You should see an abnormal resize with the shadow remaining the original size.<br/>" +
                            "Resizing it fixes it up again.</p>"));
                    w.setMinHeight(10);
                    w.setMinWidth(20);
                    w.setOnEsc(true);
                    w.show();

                }
            });
            w = new SimplePanel(b);
        }

        @Override
        public Widget asWidget() {
            return w;
        }
    }
}
