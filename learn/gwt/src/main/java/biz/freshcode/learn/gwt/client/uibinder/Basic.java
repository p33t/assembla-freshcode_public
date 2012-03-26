package biz.freshcode.learn.gwt.client.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

// NOTE: Using ResizeComposite here breaks the UI.
// Something about wrapped widget not implementing RequiresResize (?)
public class Basic extends Composite {
    interface Binder extends UiBinder<Widget, Basic> {
    }

    private static Binder binder = GWT.create(Binder.class);

    {
        initWidget(binder.createAndBindUi(this));
    }
}
