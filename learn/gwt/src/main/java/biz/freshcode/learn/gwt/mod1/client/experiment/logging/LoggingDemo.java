package biz.freshcode.learn.gwt.mod1.client.experiment.logging;

import biz.freshcode.learn.gwt.mod1.client.builder.gxt.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt.mod1.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import java.util.logging.Logger;

public class LoggingDemo extends AbstractIsWidget<FlowLayoutContainer> {
    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    protected FlowLayoutContainer createWidget() {
        return new FlowLayoutContainerBuilder()
                .add(new TextButton("Log now", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        log();
                    }
                }))
                .flowLayoutContainer;
    }

    private void log() {
        String msg = "Some noteworthy event.";
        GWT.log(msg);
        // This surfaces in the Javascript Console and probably in the LoggingPopup (if it was enabled)
        logger.info(msg);
    }
}
