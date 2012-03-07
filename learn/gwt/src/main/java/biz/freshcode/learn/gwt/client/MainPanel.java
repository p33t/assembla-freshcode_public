package biz.freshcode.learn.gwt.client;

import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuBar;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

public class MainPanel extends VLayout {
    {
        // NOTE: Seems to cause scroll bar (container is 100% height)
//        setShowEdges(true);

        MenuBar mb = new MenuBar();
        addMember(mb);
        Menu m = new Menu();
        m.setTitle("Experiments");
        MenuItem alert = new MenuItem("Alert");
        m.addItem(alert);
        alert.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent menuItemClickEvent) {
                SC.say("Self destruct sequence has initiated.");
            }
        });
        mb.setMenus(m);

        HTMLPane html = new HTMLPane();
        addMember(html);
        html.setContents("<p>This is the contents.  It carries on for a little bit but I don't know if it will get a scroll bar.</p>");
    }
}
