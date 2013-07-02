package biz.freshcode.learn.gwtp.client.popup;

import biz.freshcode.learn.gwtp.client.builder.gwt.PopupPanelBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewImpl;

public class GxtPopupView extends PopupViewImpl {
    @Inject
    public GxtPopupView(EventBus eventBus) {
        super(eventBus);
        initWidget(new PopupPanelBuilder()
                .autoHideEnabled(true)
//                .modal(true)
                .add(new HTML("<p>Yay!</p>"))
                .popupPanel);
    }
}
