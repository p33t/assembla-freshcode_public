package biz.freshcode.learn.gwt.mod1.client.experiment.busy.reuse;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.sencha.gxt.widget.core.client.Component;

import static biz.freshcode.learn.gwt.mod1.client.experiment.busy.reuse.Bundle.STYLE;

/**
 * Utility for marking a component as busy.
 */
public class BusyUtil {
    /**
     * Disables the given component and places spinner gif over top.
     */
    public static void markBusy(final Component c) {
        c.disable();
        ensureNotBusy(c);
        // NOTE: Don't add style to the component as we don't want 'spinner' to be disabled.
        c.getElement().insertFirst("<div class='" + STYLE.busySpinner() + "'/>");
    }

    /**
     * Enables the given component and removes the spinner (if any).
     */
    public static void clearBusy(Component c) {
        c.enable();
        if (!ensureNotBusy(c)) {
            GWT.log("No busy spinner to remove");
        }
    }

    private static boolean ensureNotBusy(Component c) {
        Element first = c.getElement().getFirstChildElement();
        if (first != null && first.removeClassName(STYLE.busySpinner())) {
            first.removeFromParent();
            return true;
        }
        return false;
    }
}
