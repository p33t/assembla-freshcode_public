package biz.freshcode.learn.gwtp.client.util;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * @deprecated Wholly incompatible with constructor based injection.  Lifecycle is way too messed up.
 */
public abstract class AbstractIsWidget<T extends Widget> implements IsWidget {
    private T w;

    @Override
    public final T asWidget() {
        if (w == null) w = createWidget();
        return w;
    }

    protected abstract T createWidget();
}
