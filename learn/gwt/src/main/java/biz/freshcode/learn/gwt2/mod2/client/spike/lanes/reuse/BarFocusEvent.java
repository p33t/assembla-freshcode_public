package biz.freshcode.learn.gwt2.mod2.client.spike.lanes.reuse;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Notification of when the focal gantt bar changes.
 */
public class BarFocusEvent extends GwtEvent<BarFocusEvent.Handler> {
    private static Type<Handler> TYPE = new Type<Handler>();
    private final String barIdOrNull;

    public BarFocusEvent(Object src, String barIdOrNull) {
        super.setSource(src);
        this.barIdOrNull = barIdOrNull;
    }

    public static Type<Handler> getType() {
        return TYPE;
    }

    @Override
    public Type<Handler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(Handler handler) {
        handler.focusChanged(this);
    }

    public interface Handler extends EventHandler {
        void focusChanged(BarFocusEvent evt);
    }

    public String getBarIdOrNull() {
        return barIdOrNull;
    }
}