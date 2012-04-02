package biz.freshcode.learn.gwt.client;

import biz.freshcode.learn.gwt.client.uibinder.Basic;
import biz.freshcode.learn.gwt.client.uibinder.Composed;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class MainPanel extends Composite {
    DockLayoutPanel pnl = new DockLayoutPanel(Style.Unit.EM);
    Widget content = new HTMLPanel("<p>This is the contents.  It carries on for a little bit but I don't know if it will get a scroll bar.</p>");

    {
        pnl.setHeight("100%");
        pnl.setWidth("100%");

        MenuBar mb = new MenuBar();
        pnl.addNorth(mb, 2);
        MenuBar exps = new MenuBar(true);
        mb.addItem("Experiments", exps);

        exps.addItem("UI Spike", new Command() {
            public void execute() {
                Widget w = GWT.create(biz.freshcode.learn.gwt.client.uispike.MainPanel.class);
                replaceContent(w);
            }
        });

        exps.addItem("Alert", new Command() {
            public void execute() {
                Window.alert("Consider yourself alerted");
            }
        });

        MenuBar uiBinder = uiBinder();
        exps.addItem("Ui Binder", uiBinder);

        pnl.add(content);
        initWidget(pnl);
    }

    private MenuBar uiBinder() {
        MenuBar mb = new MenuBar(true);
        mb.addItem("Basic", new Command() {
            public void execute() {
                Widget w = GWT.create(Basic.class);
                replaceContent(w);
            }
        });

        mb.addItem("Composed", new Command() {
            public void execute() {
                Widget w = GWT.create(Composed.class);
                replaceContent(w);
            }
        });
        return mb;
    }

    private void replaceContent(Widget newContent) {
        pnl.remove(content);
        content = newContent;
        pnl.add(content);
    }
}
