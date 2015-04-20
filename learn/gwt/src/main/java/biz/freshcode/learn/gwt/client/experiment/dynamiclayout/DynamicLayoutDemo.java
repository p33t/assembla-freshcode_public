package biz.freshcode.learn.gwt.client.experiment.dynamiclayout;

import biz.freshcode.learn.gwt2.common.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class DynamicLayoutDemo extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        return new TextButton("Popup", new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                Window w = new DlWindow().asWidget();
                w.show();
            }
        });
    }

}
