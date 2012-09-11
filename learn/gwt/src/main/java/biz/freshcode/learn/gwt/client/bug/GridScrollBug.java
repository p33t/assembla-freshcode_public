package biz.freshcode.learn.gwt.client.bug;

import biz.freshcode.learn.gwt.client.builder.gxt.DialogBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class GridScrollBug extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        return new HorizontalLayoutContainerBuilder()
                .add(new TextButton("Launch", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        new DialogBuilder()
                                .widget(new HTMLPanel("<p>Oy!</p>"))
                                .dialog.show();
                    }
                }))
                .horizontalLayoutContainer;
    }
}
