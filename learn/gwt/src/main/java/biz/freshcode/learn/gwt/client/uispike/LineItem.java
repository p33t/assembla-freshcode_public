package biz.freshcode.learn.gwt.client.uispike;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
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
    @UiField
    SimpleContainer pnlContent;
    @UiField
    TextButton btnEdit;
    @UiField(provided = true)
    Label lblName;
    private final Row row;

    public LineItem(Row row) {
        this.row = row;
    }

    @Override
    public Widget asWidget() {
        lblName = new Label(row.getStr());
        Component c = binder.createAndBindUi(this);
        // TODO: Replace with a template
        pnlContent.setWidget(new HTMLPanel("<p>Number: " + row.getNum() + "</p>"));
        return c;
    }

    @UiHandler("btnEdit")
    public void onEdit(SelectEvent evt) {
        Info.display("Note", "Editing " + lblName.getText());
    }

    interface Binder extends UiBinder<Component, LineItem> {
    }
}
