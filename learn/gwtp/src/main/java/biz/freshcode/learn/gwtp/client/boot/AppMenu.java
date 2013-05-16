package biz.freshcode.learn.gwtp.client.boot;

import biz.freshcode.learn.gwtp.client.builder.gxt.menu.MenuBarBuilder;
import biz.freshcode.learn.gwtp.client.builder.gxt.menu.MenuBarItemBuilder;
import biz.freshcode.learn.gwtp.client.builder.gxt.menu.MenuBuilder;
import biz.freshcode.learn.gwtp.client.builder.gxt.menu.MenuItemBuilder;
import biz.freshcode.learn.gwtp.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

@Singleton
public class AppMenu extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        return new MenuBarBuilder()
                .add(new MenuBarItemBuilder("Shakedown")
                        .menu(new MenuBuilder()
                                .add(new MenuItemBuilder()
                                        .text("Dispatch")
                                        .menuItem)
                                .menu)
                        .menuBarItem)
                .menuBar;
    }
}
