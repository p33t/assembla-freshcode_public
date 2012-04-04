package biz.freshcode.learn.gwt.client.uispike;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Component;

import java.util.logging.Logger;

public class ControlPanel implements IsWidget {
    private static Binder binder = GWT.create(Binder.class);
    Logger logger = Logger.getLogger(getClass().getName());

    public Widget asWidget() {
        return binder.createAndBindUi(this);
    }

    interface Binder extends UiBinder<Component, ControlPanel> {
    }
}
