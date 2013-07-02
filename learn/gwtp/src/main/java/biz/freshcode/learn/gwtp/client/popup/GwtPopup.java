package biz.freshcode.learn.gwtp.client.popup;

import biz.freshcode.learn.gwtp.client.builder.gwt.PopupPanelBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewImpl;
import com.gwtplatform.mvp.client.PresenterWidget;

public class GwtPopup extends PresenterWidget<GwtPopup.View> {

    @Inject
    public GwtPopup(EventBus eventBus, View view) {
        super(eventBus, view);
    }

    public static class View extends PopupViewImpl {
        @Inject
        public View(EventBus eventBus) {
            super(eventBus);
            initWidget(new PopupPanelBuilder()
                    .autoHideEnabled(true)
    //                .modal(true)
                    .add(new HTML("<p>Yay!</p>"))
                    .popupPanel);
        }
    }
}
