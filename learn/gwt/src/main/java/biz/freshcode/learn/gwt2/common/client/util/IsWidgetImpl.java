package biz.freshcode.learn.gwt2.common.client.util;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class IsWidgetImpl implements IsWidget {
    private Widget widget;

    protected void initWidget(Widget w) {
        if (isInitialised()) throw ExceptionUtil.illegalState("Can only initWidget() once.");
        widget = w;
    }

    protected boolean isInitialised() {
        return widget != null;
    }

    @Override
    public Widget asWidget() {
        if (!isInitialised()) throw ExceptionUtil.illegalState("The widget has not been initialised.");
        return widget;
    }
}
