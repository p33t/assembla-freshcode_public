package biz.freshcode.learn.gwt.mod1.client.util;

import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;

public class DummySelectHandler implements SelectEvent.SelectHandler, Runnable {
    private final String msg;

    public DummySelectHandler(String msg) {
        this.msg = msg;
    }

    @Override
    public void onSelect(SelectEvent event) {
        run();
    }

    @Override
    public void run() {
        Info.display("Event", msg);
    }
}
