package biz.freshcode.learn.gwt.client;

import biz.freshcode.learn.gwt.client.experiment.Resizer;
import biz.freshcode.learn.gwt.client.uibinder.Basic;
import biz.freshcode.learn.gwt.client.uibinder.Composed;
import biz.freshcode.learn.gwt.client.uibinder.eg.BorderLayoutEg;
import biz.freshcode.learn.gwt.client.uibinder.eg.Tutorial1;
import biz.freshcode.learn.gwt.client.uibinder.eg.Tutorial2;
import biz.freshcode.learn.gwt.client.uispike.UiSpikePanel;
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

        mb.addItem("Experiments", experiments());
        mb.addItem("GXT", mbGxt());

        pnl.add(content);
        initWidget(pnl);
    }

    private MenuBar experiments() {
        MenuBar exps = new MenuBar(true);
        exps.addItem("Alert", new Command() {
            public void execute() {
                Window.alert("Consider yourself alerted");
            }
        });

        exps.addItem("Resizer", new Command() {
            public void execute() {
                Widget w = GWT.create(Resizer.class);
                replaceContent(w);
            }
        });

        MenuBar uiBinder = uiBinder();
        exps.addItem("Ui Binder", uiBinder);
        return exps;
    }

    private MenuBar mbGxt() {
        MenuBar mbGxt = new MenuBar(true);

        mbGxt.addItem("UI Spike", new Command() {
            public void execute() {
                IsWidget w = GWT.create(UiSpikePanel.class);
                replaceRoot(w);
            }
        });

        MenuBar uibGxt = uibGxt();
        mbGxt.addItem("Ui Binder", uibGxt);
        return mbGxt;
    }

    private MenuBar uibGxt() {
        MenuBar mb = new MenuBar(true);
        mb.addItem("Border Eg", new Command() {
            public void execute() {
                IsWidget w = GWT.create(BorderLayoutEg.class);
                replaceRoot(w);
            }
        });

        mb.addItem("Tutorial 1 (simple frame)", new Command() {
            public void execute() {
                IsWidget w = GWT.create(Tutorial1.class);
                replaceRoot(w);
            }
        });

        mb.addItem("Tutorial 2 (menu + event)", new Command() {
            public void execute() {
                IsWidget w = GWT.create(Tutorial2.class);
                replaceRoot(w);
            }
        });
        return mb;
    }

    private void replaceRoot(IsWidget w) {
        RootPanel root = RootPanel.get();
        root.clear();
        root.add(w.asWidget());
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
