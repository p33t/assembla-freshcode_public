package biz.freshcode.learn.gwt.mod1.client.experiment;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Resizer extends Composite {
    interface Binder extends UiBinder<Widget, Composite> {
    }

    private static Binder binder = GWT.create(Binder.class);
    {
        initWidget(binder.createAndBindUi(this));
    }
}
