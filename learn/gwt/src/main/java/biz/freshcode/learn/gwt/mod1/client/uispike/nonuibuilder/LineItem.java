package biz.freshcode.learn.gwt.mod1.client.uispike.nonuibuilder;

import biz.freshcode.learn.gwt.mod1.client.uispike.Row;
import biz.freshcode.learn.gwt.mod1.client.uispike.RowBuilder;
import biz.freshcode.learn.gwt.mod1.client.uispike.RowRenderer;
import biz.freshcode.learn.gwt.mod1.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class LineItem extends AbstractIsWidget {
    private static final RowRenderer renderer = GWT.create(RowRenderer.class);

    private final Row row;

    public LineItem(Row row) {
        this.row = row;
    }

    public Row getRow() {
        return new RowBuilder()
                .initFrom(row)
                .row;
    }

    @Override
    protected Widget createWidget() {
        VerticalLayoutContainer c = new VerticalLayoutContainer();
        ToolBar tb = new ToolBar();
        tb.add(new LabelToolItem(row.getStr()));
        tb.add(new FillToolItem());
        tb.add(new TextButton("Edit...", new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                onEdit();
            }
        }));
        c.add(tb);
        c.add(new HTMLPanel(renderer.render(row)));
        return c;
    }

    private void onEdit() {
        Info.display("Note", "Editing " + row.getStr());
    }
}
