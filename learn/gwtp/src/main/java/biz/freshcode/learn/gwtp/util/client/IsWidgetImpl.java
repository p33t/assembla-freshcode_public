package biz.freshcode.learn.gwtp.util.client;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import static biz.freshcode.learn.gwtp.util.shared.ExceptionUtil.illegalState;

/**
 * A typed implementation of IsWidget.
 */
public class IsWidgetImpl<T extends Widget> implements IsWidget {
    private T w;

    @Override
    public final T asWidget() {
        if (w == null) throw illegalState("Widget not initialized.");
        return w;
    }

    protected void initWidget(T t) {
        w = t;
    }
}
