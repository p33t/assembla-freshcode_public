package biz.freshcode.learn.gwt.client;

import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.MenuBar;

public class MainPanel extends VLayout {
    {
        // NOTE: Seems to cause scroll bar (container is 100% height)
//        setShowEdges(true);

//        MenuBar mb = new MenuBar();
        HTMLPane html = new HTMLPane();
        html.setContents("<p>This is the contents.  It carries on for a little bit but I don't know if it will get a scroll bar.</p>");
        addMember(html);
    }
}
