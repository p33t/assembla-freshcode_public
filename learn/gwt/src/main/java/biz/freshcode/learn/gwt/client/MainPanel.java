package biz.freshcode.learn.gwt.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainPanel extends Composite {
    {
        VerticalPanel vp = new VerticalPanel();
        vp.setHeight("100%");
        vp.setWidth("100%");

        MenuBar mb = new MenuBar();
        vp.add(mb);
        MenuBar exps = new MenuBar(true);
        mb.addItem("Experiments", exps);
        exps.addItem("Alert", new Command() {
            @Override
            public void execute() {
                Window.alert("Consider yourself alerted");
            }
        });

        HTMLPanel html = new HTMLPanel("<p>This is the contents.  It carries on for a little bit but I don't know if it will get a scroll bar.</p>");
        vp.add(html);
        initWidget(vp);
    }
}
