package biz.freshcode.learn.gwt.client.uispike.gxt;

import biz.freshcode.learn.gwt.client.uispike.Row;
import biz.freshcode.learn.gwt.client.uispike.RowRenderer;
import biz.freshcode.learn.gwt.client.uispike.builder.RowBuilder;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;

public class LineItem implements IsWidget {
    private static final Binder binder = GWT.create(Binder.class);
    private static final RowRenderer renderer = GWT.create(RowRenderer.class);

    @UiField(provided = true)
    HTMLPanel pnlContent;
    @UiField
    TextButton btnEdit;
    @UiField(provided = true)
    LabelToolItem lblName;
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
    public Widget asWidget() {
        lblName = new LabelToolItem(row.getStr());
        pnlContent = new HTMLPanel(renderer.render(row));
        return binder.createAndBindUi(this);
    }

    @UiHandler("btnEdit")
    public void onEdit(SelectEvent evt) {
        Info.display("Note", "Editing " + lblName.getLabel());
    }

    interface Binder extends UiBinder<Component, LineItem> {
    }

}
