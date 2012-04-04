package biz.freshcode.learn.gwt.client.uispike;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;

import java.util.logging.Logger;

//  NOTE: Using 'Composite' doesn't play well with GXT.  IsWidget seems to work fine.
public class UiSpikePanel implements IsWidget {
    private static Binder binder = GWT.create(Binder.class);
    Logger logger = Logger.getLogger(getClass().getName());

    public Widget asWidget() {
        return binder.createAndBindUi(this);
    }

    interface Binder extends UiBinder<Component, UiSpikePanel> {
    }

    /**
     * Supplies fiddley ui configuration
     */
    static class UiConfig {
        BorderLayoutData westLayout() {
            return new BorderLayoutDataBuilder()
                    .size(200)
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
