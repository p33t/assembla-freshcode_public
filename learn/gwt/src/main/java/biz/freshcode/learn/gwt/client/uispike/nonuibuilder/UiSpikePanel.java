package biz.freshcode.learn.gwt.client.uispike.nonuibuilder;

import biz.freshcode.learn.gwt.client.uispike.Row;
import biz.freshcode.learn.gwt.client.uispike.builder.BorderLayoutDataBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.MarginsBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;

import java.util.ArrayList;
import java.util.logging.Logger;

//  NOTE: Using 'Composite' doesn't play well with GXT.  IsWidget seems to work fine.
public class UiSpikePanel implements IsWidget, ControlPanel.Host {
    Logger logger = Logger.getLogger(getClass().getName());
    private ContentPanel pnlCenter;

    public Widget asWidget() {
        Viewport v = new Viewport();
        BorderLayoutContainer b = new BorderLayoutContainer();
        // NOTE: We still need the content panel here to get resizing to work.
        ContentPanel west = new ContentPanel();
        west.setWidget(new ControlPanel(this));
        b.setWestWidget(west, new BorderLayoutDataBuilder()
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
                .borderLayoutData);
        pnlCenter = new ContentPanel();
        ContentPanel center = pnlCenter;
        center.setWidget(new HTMLPanel("<p>This is the default center content.</p>"));
        b.setCenterWidget(center);
        v.setWidget(b);
        return v;
    }

    @Override
    public void notifyProcess(ArrayList<Row> rows) {
        logger.info("Processing " + rows);
        String content = SafeHtmlUtils.htmlEscape(rows.toString());
        pnlCenter.setWidget(new HTMLPanel(content));
    }
}
