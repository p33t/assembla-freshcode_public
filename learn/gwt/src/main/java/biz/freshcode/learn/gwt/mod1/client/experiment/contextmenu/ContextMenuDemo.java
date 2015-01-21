package biz.freshcode.learn.gwt.mod1.client.experiment.contextmenu;

import biz.freshcode.learn.gwt.mod1.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.menu.MenuBuilder;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class ContextMenuDemo extends AbstractIsWidget {

    private Menu mnu = new MenuBuilder()
            .add(new MenuItem("Item 1", new SelectionHandler<MenuItem>() {
                @Override
                public void onSelection(SelectionEvent<MenuItem> event) {
                    Info.display("Menu Item Clicked", "Item 1");
                }
            }))
            .menu;

    @Override
    protected Widget createWidget() {
        ToolButton toolButton = new ToolButton(ToolButton.MAXIMIZE, new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                showMenu();   //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Left click
            }
        });
        toolButton.setContextMenu(mnu);  // <<<<<<<<<<<<<<<<<<<<<<<<<< Right click
        return toolButton;
    }

    private void showMenu() {
        Widget w = asWidget();
        mnu.showAt(w.getAbsoluteLeft(), w.getAbsoluteTop() + w.getOffsetHeight());
    }
}
