package biz.freshcode.learn.gwt.client.util;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * A default implementation of IsWidget.
 * This is used to supply 'IsWidget' instances when only a 'Widget' is available.
 */
public class AsIsWidget implements IsWidget {
    private final Widget widget;

    public AsIsWidget(Widget widget) {
        this.widget = widget;
    }
    
    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public int hashCode() {
        return widget.hashCode();
    }

    @Override
    /**
     * Enables identification of the original IsWidget.
     */
    public boolean equals(Object obj) {
        //noinspection SimplifiableIfStatement
        if (obj instanceof IsWidget) return ((IsWidget) obj).asWidget().equals(widget);
        else return false;
    }
}
