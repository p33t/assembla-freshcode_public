package biz.freshcode.learn.gwt.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainPanel extends Composite {
    {
        // NOTE: Seems to cause scroll bar (container is 100% height)
//        setShowEdges(true);

//        MenuBar mb = new MenuBar();
//        addMember(mb);
//        Menu m = new Menu();
//        m.setTitle("Experiments");
//        MenuItem alert = new MenuItem("Alert");
//        m.addItem(alert);
//        alert.addClickHandler(new ClickHandler() {
//            @Override
//            public void onClick(MenuItemClickEvent menuItemClickEvent) {
//                SC.say("Self destruct sequence has initiated.");
//            }
//        });
//        mb.setMenus(m);

        VerticalPanel vp = new VerticalPanel();
        HTMLPanel html = new HTMLPanel("<p>This is the contents.  It carries on for a little bit but I don't know if it will get a scroll bar.</p>");
        vp.add(html);
        initWidget(vp);
    }
}
