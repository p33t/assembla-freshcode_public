package biz.freshcode.learn.gwt.client.uispike;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;

import java.util.logging.Logger;

public class MainPanel implements IsWidget {
    private static Binder binder = GWT.create(Binder.class);
    Logger logger = Logger.getLogger(getClass().getName());

    public Widget asWidget() {
        return binder.createAndBindUi(this);
    }

    interface Binder extends UiBinder<Component, MainPanel> {
    }

    /**
     * Supplies fiddley ui configuration
     */
    static class UiConfig {
        BorderLayoutData westLayout() {
            return new BorderLayoutDataBuilder()
                    .size(100)
                    .collapsible(true)
                    .split(true)
                    .collapseMini(true)
                    .margins(new MarginsBuilder()
                            .top(0)
                            .right(5)
                            .bottom(0)
                            .left(5)
                            .margins)
                    .borderLayoutData;
        }
    }
}
