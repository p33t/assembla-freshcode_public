package biz.freshcode.learn.gwt.client.experiment.events;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class CustomEventGwtTest extends GWTTestCase {

    public void test() {
        CustomModel model = new CustomModel();
        MyHandler handler = new MyHandler();
        HandlerRegistration rego = model.addHandler(handler);
        model.eventTrigger();
        assertEquals(1, handler.eventCount);
        rego.removeHandler();
        model.eventTrigger();
        assertEquals(1, handler.eventCount);
    }

    @Override
    public String getModuleName() {
        return "biz.freshcode.learn.gwt.Mod1";
    }

    static class MyHandler implements CustomEvent.Handler {
        int eventCount = 0;

        @Override
        public void onEvent(CustomEvent evt) {
            eventCount ++;
        }
    }
}
