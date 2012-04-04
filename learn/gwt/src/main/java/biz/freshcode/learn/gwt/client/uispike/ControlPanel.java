package biz.freshcode.learn.gwt.client.uispike;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectionEvent;
import com.sencha.gxt.widget.core.client.info.Info;

import java.util.logging.Logger;

public class ControlPanel implements IsWidget {
    private static Binder binder = GWT.create(Binder.class);
    Logger logger = Logger.getLogger(getClass().getName());

    @UiField
    VerticalLayoutContainer north;
    @UiField
    TextButton btnGo;

    public Widget asWidget() {
        Component c = binder.createAndBindUi(this);
        north.setScrollMode(ScrollSupport.ScrollMode.AUTOY);
        return c;
    }


    @UiHandler("btnGo")
    public void onGo(SelectEvent evt) {
        Info.display("Note", "'Go' clicked");
    }


    interface Binder extends UiBinder<Component, ControlPanel> {
    }

    static class UiConfig {
        BorderLayoutData northLayout() {
            return new BorderLayoutDataBuilder()
                    .split(true)
                    .margins(new MarginsBuilder()
                            .bottom(5)
                            .margins)
                    .borderLayoutData;
        }
    }
}
