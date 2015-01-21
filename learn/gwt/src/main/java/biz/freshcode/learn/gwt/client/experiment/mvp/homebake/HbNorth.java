package biz.freshcode.learn.gwt.client.experiment.mvp.homebake;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class HbNorth implements IsWidget {

    private final Host host;
    private TextButton btn;

    public HbNorth(Host host) {
        this.host = host;
    }

    @Override
    public Widget asWidget() {
        return new HorizontalLayoutContainerBuilder()
                .add(btn = new TextButton("xxx", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        host.notifyAdvance();
                    }
                }))
                .horizontalLayoutContainer;
    }

    public void setButtonText(String txt) {
        btn.setText(txt);
    }

    // Prevents cyclic dependency
    public interface Host {
        void notifyAdvance();
    }
}
