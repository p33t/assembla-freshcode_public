package biz.freshcode.learn.gwt.client;

import biz.freshcode.learn.gwt.client.uibinder.Basic;
import biz.freshcode.learn.gwt.client.uibinder.Composed;
import biz.freshcode.learn.gwt.client.uibinder.PanelMagic;
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
        exps.addItem("Alert", new Command() {
            @Override
            public void execute() {
                Window.alert("Consider yourself alerted");
            }
        });

        MenuBar uiBinder = new MenuBar(true);
        exps.addItem("Ui Binder", uiBinder);

        // NOTE: Tried to replace this with cls.newInstance() but GWT barfed.
        uiBinder.addItem("Basic", new Command() {
            @Override
            public void execute() {
                replaceContent(new Basic());
            }
        });

        uiBinder.addItem("Composed", new Command() {
            @Override
            public void execute() {
                replaceContent(new Composed());
            }
        });
        pnl.add(content);
        initWidget(pnl);
    }

    private void replaceContent(Widget newContent) {
        pnl.remove(content);
        content = newContent;
        pnl.add(content);
    }
}
