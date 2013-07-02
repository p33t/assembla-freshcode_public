package biz.freshcode.learn.gwtp.client.popup;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;

public class ThePopup extends PresenterWidget<ThePopupView> {

    @Inject
    public ThePopup(EventBus eventBus, ThePopupView view) {
        super(eventBus, view);
    }
}
