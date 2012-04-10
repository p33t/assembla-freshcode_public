package biz.freshcode.learn.gwt.client.uispike.nonuibuilder;

import biz.freshcode.learn.gwt.client.uispike.Row;
import biz.freshcode.learn.gwt.client.uispike.builder.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.BorderLayoutDataBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.VerticalLayoutContainerBuilder;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import java.util.ArrayList;
import java.util.logging.Logger;

public class ControlPanel implements IsWidget {
    final Logger logger = Logger.getLogger(getClass().getName());
    final Host host;

    VerticalLayoutContainer lines = new VerticalLayoutContainerBuilder()
            .scrollMode(ScrollSupport.ScrollMode.AUTOY)
            .verticalLayoutContainer;

    ArrayList<LineItem> items = new ArrayList<LineItem>();

    public ControlPanel(Host host) {
        this.host = host;
    }

    public Widget asWidget() {
        ToolBar tb = new ToolBar();
        tb.add(new FillToolItem());
        tb.add(new TextButton("Process", new SelectEvent.SelectHandler() {
            public void onSelect(SelectEvent event) {
                process();
            }
        }));
        tb.add(new SeparatorToolItem());
        tb.add(new TextButton("Add", new SelectEvent.SelectHandler() {
            public void onSelect(SelectEvent event) {
                add();
            }
        }));

        return new BorderLayoutContainerBuilder()
                .northWidget(tb, new BorderLayoutDataBuilder()
                        .size(40) // Would have been nice for toolbar to automatically set container height
                        .borderLayoutData
                )
                .centerWidget(lines)
                .borderLayoutContainer;
    }

    private void process() {
        ArrayList<Row> rows = new ArrayList<Row>();
        for (LineItem line : items) {
            rows.add(line.getRow());
        }
        host.notifyProcess(rows);
    }

    private void add() {
        Row row = new Row();
        int i = Random.nextInt();
        row.setNum(i);
        row.setStr("#" + i);
        LineItem line = new LineItem(row);
        lines.add(line);
        items.add(line);
        logger.info("Added line #" + i);
    }

    interface Host {
        // NOTE: More efficient to use a concrete implementation
        void notifyProcess(ArrayList<Row> rows);
    }
}
