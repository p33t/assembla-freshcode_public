package biz.freshcode.learn.gwtp.client.popup;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;

public class GwtPopup extends PresenterWidget<GxtPopupView> {

    @Inject
    public GwtPopup(EventBus eventBus, GxtPopupView view) {
        super(eventBus, view);
    }
}
