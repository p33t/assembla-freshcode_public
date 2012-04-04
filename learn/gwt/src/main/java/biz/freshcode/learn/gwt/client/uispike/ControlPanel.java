package biz.freshcode.learn.gwt.client.uispike;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

import java.util.logging.Logger;

public class ControlPanel implements IsWidget {
    private static Binder binder = GWT.create(Binder.class);
    Logger logger = Logger.getLogger(getClass().getName());
    @UiField
    VerticalLayoutContainer north;

    public Widget asWidget() {
        Component c = binder.createAndBindUi(this);
        north.setScrollMode(ScrollSupport.ScrollMode.AUTOY);
        return c;
    }

    interface Binder extends UiBinder<Component, ControlPanel> {
    }
}
