package biz.freshcode.learn.gwt.client.experiment.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class CustomEvent extends GwtEvent<CustomEvent.Handler> {
    private static Type<Handler> TYPE = new Type<Handler>();

    public CustomEvent(CustomModel src) {
        super.setSource(src);
    }

    public static Type<Handler> getType() {
        return TYPE;
    }
    
    @Override
    public Type<Handler> getAssociatedType() {
        return TYPE;
    }

    @Override
    public CustomModel getSource() {
        return (CustomModel) super.getSource();
    }

    @Override
    protected void dispatch(Handler handler) {
        handler.onEvent(this);
    }

    public interface Handler extends EventHandler {
        void onEvent(CustomEvent evt);
    }
}
