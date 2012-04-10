package biz.freshcode.learn.gwt.client.uispike.gxt;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;

import java.util.ArrayList;

public class LineItem implements IsWidget {
    private static final Binder binder = GWT.create(Binder.class);
    private static final RowRenderer renderer = GWT.create(RowRenderer.class);

    @UiField(provided = true)
    HTMLPanel pnlContent;
    @UiField
    TextButton btnEdit;
    @UiField(provided = true)
    Label lblName;
    private final Row row;

    public LineItem(Row row) {
        this.row = row;
    }

    public Row getRow() {
        Row r = new Row();
        r.setNum(row.getNum());
        r.setStr(row.getStr());
        return r;
    }

    @Override
    public Widget asWidget() {
        lblName = new Label(row.getStr());
        pnlContent = new HTMLPanel(renderer.render(row));
        return binder.createAndBindUi(this);
    }

    @UiHandler("btnEdit")
    public void onEdit(SelectEvent evt) {
        Info.display("Note", "Editing " + lblName.getText());
    }

    interface Binder extends UiBinder<Component, LineItem> {
    }

    interface RowRenderer extends XTemplates {
        @XTemplate(source = "row.html")
        public SafeHtml render(Row row);
    }
}
