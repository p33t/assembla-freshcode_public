package biz.freshcode.learn.gwt.client.uispike;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import java.util.logging.Logger;

public class MainPanel extends Composite {
    Logger logger = Logger.getLogger(getClass().getName());

    interface Binder extends UiBinder<Widget, MainPanel> {
    }

    private static Binder binder = GWT.create(Binder.class);

    {
        initWidget(binder.createAndBindUi(this));
    }
}
