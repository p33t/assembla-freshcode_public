package biz.freshcode.learn.gwt.client.uispike;

import biz.freshcode.learn.gwt.client.uispike.builder.BorderLayoutDataBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.MarginsBuilder;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.NorthSouthContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;

import java.util.logging.Logger;

public class ControlPanel implements IsWidget {
    private static Binder binder = GWT.create(Binder.class);
    Logger logger = Logger.getLogger(getClass().getName());

    @UiField
    TextButton btnGo;
    @UiField
    VerticalLayoutContainer lines;

    public Widget asWidget() {
        Component c = binder.createAndBindUi(this);
        lines.setScrollMode(ScrollSupport.ScrollMode.AUTOY);
        return c;
    }

    @UiHandler("btnGo")
    public void onGo(SelectEvent evt) {
        Row row = new Row();
        int i = Random.nextInt();
        row.setNum(i);
        row.setStr("#" + i);
        LineItem line = new LineItem(row);
        lines.add(line);
        logger.info("Added line #" + i);
    }

    interface Binder extends UiBinder<Component, ControlPanel> {
    }

}
