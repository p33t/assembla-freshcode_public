package biz.freshcode.learn.gwtp.util.client;

import com.google.gwt.user.client.ui.Widget;

public class WidgetUtil {

    /**
     * Null safe 'removeFromParent()'
     * @see com.google.gwt.user.client.ui.Widget#removeFromParent()
     */
    public static void removeFromParent(Widget w) {
        if (w != null) w.removeFromParent();
    }
}
