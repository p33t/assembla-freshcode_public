package biz.freshcode.learn.gwt.client;

import biz.freshcode.learn.gwt.client.uibinder.Basic;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import java.util.Date;

public class MainPanel extends Composite {
    Widget content = new HTMLPanel("<p>This is the contents.  It carries on for a little bit but I don't know if it will get a scroll bar.</p>");

    {
        final FlowPanel fp = new FlowPanel();
        fp.setHeight("100%");
        fp.setWidth("100%");

        MenuBar mb = new MenuBar();
        fp.add(mb);
        MenuBar exps = new MenuBar(true);
        mb.addItem("Experiments", exps);
        exps.addItem("Alert", new Command() {
            @Override
            public void execute() {
                Window.alert("Consider yourself alerted");
            }
        });
        exps.addItem("Basic Binder", new Command() {
            @Override
            public void execute() {
                fp.remove(content);
                content = new Basic();
                fp.add(content);
            }
        });

        fp.add(content);
        initWidget(fp);
    }
}
