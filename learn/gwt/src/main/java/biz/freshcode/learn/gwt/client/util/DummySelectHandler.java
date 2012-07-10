package biz.freshcode.learn.gwt.client.util;

import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;

public class DummySelectHandler implements SelectEvent.SelectHandler {
    private final String msg;

    public DummySelectHandler(String msg) {
        this.msg = msg;
    }

    @Override
    public void onSelect(SelectEvent event) {
        Info.display("Event", msg);
    }
}
