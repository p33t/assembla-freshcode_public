package biz.freshcode.learn.gwt.client.uispike.nonuibuilder;

import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.Row;
import biz.freshcode.learn.gwt.client.uispike.builder.BorderLayoutDataBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.ToolBarBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;

import java.util.ArrayList;
import java.util.logging.Logger;

public class ControlPanel extends AbstractIsWidget {
    final Logger logger = Logger.getLogger(getClass().getName());
    final Host host;

    VerticalLayoutContainer lines;

    ArrayList<LineItem> items = new ArrayList<LineItem>();

    public ControlPanel(Host host) {
        this.host = host;
    }

    @Override
    protected Widget createWidget() {
        return new BorderLayoutContainerBuilder()
                .northWidget(new ToolBarBuilder()
                        .add(new FillToolItem())
                        .add(new TextButton("Process", new SelectEvent.SelectHandler() {
                            public void onSelect(SelectEvent event) {
                                process();
                            }
                        }))
                        .add(new SeparatorToolItem())
                        .add(new TextButton("Add", new SelectEvent.SelectHandler() {
                            public void onSelect(SelectEvent event) {
                                add();
                            }
                        }))
                        .toolBar,
                        new BorderLayoutDataBuilder()
                                .size(40) // Would have been nice for toolbar to automatically set container height
                                .borderLayoutData
                )
                .centerWidget(lines = new VerticalLayoutContainerBuilder()
                        .scrollMode(ScrollSupport.ScrollMode.AUTOY)
                        .verticalLayoutContainer
                )
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
