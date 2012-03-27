package biz.freshcode.learn.gwt.client.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Composed extends Composite {
    interface Binder extends UiBinder<Widget, Composed> {
    }

    private static Binder binder = GWT.create(Binder.class);

    {
        initWidget(binder.createAndBindUi(this));
    }
}
