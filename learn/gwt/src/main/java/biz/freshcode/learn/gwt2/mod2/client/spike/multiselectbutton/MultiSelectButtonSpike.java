package biz.freshcode.learn.gwt2.mod2.client.spike.multiselectbutton;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.button.TextButtonBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.menu.MenuBuilder;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.user.client.Command;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.CheckChangeEvent;
import com.sencha.gxt.widget.core.client.menu.CheckMenuItem;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.tree.Tree;

public class MultiSelectButtonSpike extends Presenter<MultiSelectButtonSpike.View, MultiSelectButtonSpike.Proxy> {
    public static final String TOKEN = "multiSelectButtonSpike";

    @Inject
    public MultiSelectButtonSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends ProxyPlace<MultiSelectButtonSpike> {
    }

    public static class View extends ViewImpl {

        @Inject
        public View() {
            final Menu mnu;
            final TextButton btn;
            final CheckMenuItem chk1;
            final CheckMenuItem chk2;
            final CheckMenuItem chkAll;
            initWidget(new HorizontalLayoutContainerBuilder()
                    .add(btn = new TextButtonBuilder()
                            .text("Checks:")
                            .menu(mnu = new MenuBuilder()
                                    .add(chkAll = new CheckMenuItem("(All)"))
                                    .add(chk1 = new CheckMenuItem("Check 1"))
                                    .add(chk2 = new CheckMenuItem("Check 2"))
                                    .add(new MenuItem("Done"))
                                    .menu)
                            .textButton)
                    .horizontalLayoutContainer);
            mnu.addBeforeSelectionHandler(new BeforeSelectionHandler<Item>() {
                @Override
                public void onBeforeSelection(BeforeSelectionEvent<Item> event) {
                    if (event.getItem() instanceof CheckMenuItem) event.cancel();
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            int i = 0;
                            if (chk1.isChecked()) i++;
                            if (chk2.isChecked()) i++;
                            btn.setText("Checks: " + i + "/2");
                        }
                    });
                }
            });

            chkAll.addCheckChangeHandler(new CheckChangeEvent.CheckChangeHandler<CheckMenuItem>() {
                @Override
                public void onCheckChange(CheckChangeEvent<CheckMenuItem> event) {
                    boolean checked = event.getChecked() == Tree.CheckState.CHECKED;
                    chk1.setChecked(checked);
                    chk2.setChecked(checked);
                }
            });
        }
    }
}
