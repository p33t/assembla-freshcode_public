package biz.freshcode.learn.gwt.client.uispike.nonuibuilder;

import biz.freshcode.learn.gwt.client.uispike.Row;
import biz.freshcode.learn.gwt.client.uispike.builder.*;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;

import java.util.ArrayList;
import java.util.logging.Logger;

public class UiSpikePanel implements IsWidget, ControlPanel.Host {
    Logger logger = Logger.getLogger(getClass().getName());
    private ContentPanel pnlCenter = new ContentPanelBuilder()
            .widget(new HTMLPanel("<p>This is the default center content.</p>"))
            .contentPanel;

    public Widget asWidget() {
        return new ViewportBuilder()
                .widget(new BorderLayoutContainerBuilder()
                        .westWidget(
                                // NOTE: We still need the content panel here to get resizing to work.
                                new ContentPanelBuilder()
                                        .widget(new ControlPanel(this))
                                        .contentPanel,
                                new BorderLayoutDataBuilder()
                                        .size(200)
                                        .collapsible(true)
                                        .split(true)
                                        .collapseMini(true)
                                        .margins(new MarginsBuilder()
                                                .top(0)
                                                .right(5)
                                                .bottom(0)
                                                .left(5)
                                                .margins
                                        )
                                        .borderLayoutData
                        )
                        .centerWidget(pnlCenter)
                        .borderLayoutContainer
                )
                .viewport;
    }

    @Override
    public void notifyProcess(ArrayList<Row> rows) {
        logger.info("Processing " + rows);
        String content = SafeHtmlUtils.htmlEscape(rows.toString());
        pnlCenter.setWidget(new HTMLPanel(content));
    }
}
