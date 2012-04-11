package biz.freshcode.learn.gwt.client.experiment.celltable;

import biz.freshcode.learn.gwt.client.uispike.builder.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.HTMLPanelBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.SimpleContainerBuilder;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import java.util.logging.Logger;

public class CellTableDemo implements IsWidget {
    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public Widget asWidget() {
        String highlight = Bundle.INSTANCE.style().highlight();
        return new BorderLayoutContainerBuilder()
//                .stylePrimaryName(highlight)
                .centerWidget(new HTMLPanelBuilder(new HTMLPanel("<p>Bruce Lee</p>"))
                        .stylePrimaryName(highlight)
                        .hTMLPanel
                ).borderLayoutContainer;
    }
}
