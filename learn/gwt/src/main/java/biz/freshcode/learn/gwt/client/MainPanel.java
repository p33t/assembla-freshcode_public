package biz.freshcode.learn.gwt.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class MainPanel extends Composite {
    {
        FlowPanel fp = new FlowPanel();
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

        HTMLPanel html = new HTMLPanel("<p>This is the contents.  It carries on for a little bit but I don't know if it will get a scroll bar.</p>");
        fp.add(html);
        initWidget(fp);
    }
}
