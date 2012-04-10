package biz.freshcode.learn.gwt.client.uispike.gxt;

import biz.freshcode.learn.gwt.client.uispike.Row;
import biz.freshcode.learn.gwt.client.uispike.builder.BorderLayoutDataBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.MarginsBuilder;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;

import java.util.ArrayList;
import java.util.logging.Logger;

//  NOTE: Using 'Composite' doesn't play well with GXT.  IsWidget seems to work fine.
public class UiSpikePanel implements IsWidget, ControlPanel.Host {
    private static Binder binder = GWT.create(Binder.class);
    Logger logger = Logger.getLogger(getClass().getName());

    @UiField (provided = true)
    ControlPanel pnlControl;
    @UiField
    ContentPanel pnlCenter;

    public Widget asWidget() {
        pnlControl = new ControlPanel(this);
        return binder.createAndBindUi(this);
    }

    @Override
    public void notifyProcess(ArrayList<Row> rows) {
        logger.info("Processing " + rows);
        String content = SafeHtmlUtils.htmlEscape(rows.toString());
        pnlCenter.setWidget(new HTMLPanel(content));
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
