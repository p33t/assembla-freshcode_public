package biz.freshcode.learn.gwt.client.uispike;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import java.util.logging.Logger;

public class ControlPanel extends Composite {
    private static Binder binder = GWT.create(Binder.class);
    Logger logger = Logger.getLogger(getClass().getName());

    {
        initWidget(binder.createAndBindUi(this));
    }

    interface Binder extends UiBinder<Widget, ControlPanel> {
    }
}
