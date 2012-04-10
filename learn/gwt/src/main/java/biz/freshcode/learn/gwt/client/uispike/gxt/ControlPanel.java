package biz.freshcode.learn.gwt.client.uispike.gxt;

import biz.freshcode.learn.gwt.client.uispike.Row;
import biz.freshcode.learn.gwt.client.uispike.builder.BorderLayoutDataBuilder;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import java.util.ArrayList;
import java.util.logging.Logger;

public class ControlPanel implements IsWidget {
    private static Binder binder = GWT.create(Binder.class);
    final Logger logger = Logger.getLogger(getClass().getName());
    final Host host;

    @UiField
    TextButton btnAdd;
    @UiField
    VerticalLayoutContainer lines;
    @UiField
    TextButton btnProcess;

    ArrayList<LineItem> items = new ArrayList<LineItem>();

    public ControlPanel(Host host) {
        this.host = host;
    }

    public Widget asWidget() {
        Component c = binder.createAndBindUi(this);
        lines.setScrollMode(ScrollSupport.ScrollMode.AUTOY);
        return c;
    }

    @UiHandler("btnProcess")
    public void handleSelect(SelectEvent event) {
        ArrayList<Row> rows = new ArrayList<Row>();
        for (LineItem line: items) {
            rows.add(line.getRow());
        }
        host.notifyProcess(rows);
    }

    @UiHandler("btnAdd")
    public void onGo(SelectEvent evt) {
        Row row = new Row();
        int i = Random.nextInt();
        row.setNum(i);
        row.setStr("#" + i);
        LineItem line = new LineItem(row);
        lines.add(line);
        items.add(line);
        logger.info("Added line #" + i);
    }

    interface Binder extends UiBinder<Component, ControlPanel> {
    }

    static class Config {
        BorderLayoutData northLayout() {
            return new BorderLayoutDataBuilder()
                    .size(40) // Would have been nice for toolbar to automatically set container height
                    .borderLayoutData;
        }
    }

    interface Host {
        // NOTE: More efficient to use a concrete implementation
        void notifyProcess(ArrayList<Row> rows);
    }
}
