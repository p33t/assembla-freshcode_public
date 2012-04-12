package biz.freshcode.learn.gwt.client.util;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractIsWidget implements IsWidget {
    private Widget w;

    @Override
    public final Widget asWidget() {
        if (w == null) w = createWidget();
        return w;
    }

    protected abstract Widget createWidget();
}
