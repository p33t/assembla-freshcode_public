package biz.freshcode.learn.gwt.client.util;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractIsWidget<T extends Widget> implements IsWidget {
    private T w;

    @Override
    public final T asWidget() {
        if (w == null) w = createWidget();
        return w;
    }

    protected abstract T createWidget();
}
