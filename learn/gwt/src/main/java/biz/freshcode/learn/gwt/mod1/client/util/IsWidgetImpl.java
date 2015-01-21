package biz.freshcode.learn.gwt.mod1.client.util;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import static biz.freshcode.learn.gwt.mod1.client.util.ExceptionUtil.illegalState;

public class IsWidgetImpl implements IsWidget {
    private Widget widget;

    protected void initWidget(Widget w) {
        if (isInitialised()) throw illegalState("Can only initWidget() once.");
        widget = w;
    }

    protected boolean isInitialised() {
        return widget != null;
    }

    @Override
    public Widget asWidget() {
        if (!isInitialised()) throw illegalState("The widget has not been initialised.");
        return widget;
    }
}
