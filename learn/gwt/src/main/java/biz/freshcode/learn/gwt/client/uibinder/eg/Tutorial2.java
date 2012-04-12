package biz.freshcode.learn.gwt.client.uibinder.eg;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.event.SelectionEvent;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

/**
 * From http://www.sencha.com/learn/ext-gwt-3-declarative-markup-with-uibinder
 */
public class Tutorial2 implements IsWidget {
    interface Binder extends UiBinder<Component, Tutorial2> {
    }

    private static Binder binder = GWT.create(Binder.class);

    @UiField
    MenuItem newFile;

    @Override
    public Widget asWidget() {
        return binder.createAndBindUi(this);
    }

    @UiHandler("newFile")
    public void onNewFile(SelectionEvent<Item> event) {
        Info.display("New File", "You have clicked on the New File MenuItem");
    }
}
