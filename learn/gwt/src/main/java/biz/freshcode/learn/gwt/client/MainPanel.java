package biz.freshcode.learn.gwt.client;

import com.smartgwt.client.widgets.HTMLPane;

public class MainPanel extends HTMLPane {
    {
        // NOTE: Seems to cause scroll bar (container is 100% height)
//        setShowEdges(true);
        setContents("<p>This is the contents.  It carries on for a little bit but I don't know if it will get a scroll bar.</p>");
    }
}
