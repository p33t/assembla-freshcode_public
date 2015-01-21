package biz.freshcode.learn.gwt.client.experiment.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class CustomModel {
    private EventBus bus = new SimpleEventBus();

    public void eventTrigger() {
        bus.fireEvent(new CustomEvent(this));
    }

    public HandlerRegistration addHandler(CustomEvent.Handler h) {
        return bus.addHandler(CustomEvent.getType(), h);
    }
}
