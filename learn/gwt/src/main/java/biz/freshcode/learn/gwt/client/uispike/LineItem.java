package biz.freshcode.learn.gwt.client.uispike;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

import java.util.ArrayList;

public class LineItem implements IsWidget {
    private static final Binder binder = GWT.create(Binder.class);
    @UiField(provided = true)
    HTMLPanel pnlContent;
    @UiField
    TextButton btnEdit;
    @UiField(provided = true)
    Label lblName;

    public LineItem(Row row) {
        // TODO: Replace with a template
        pnlContent = new HTMLPanel("<p>Numer: " + row.getNum() + "</p>");
        lblName = new Label(row.getStr());
    }

    @Override
    public Widget asWidget() {
        return binder.createAndBindUi(this);
    }

    interface Binder extends UiBinder<Component, LineItem> {
    }
}
