package biz.freshcode.learn.gwtp.client.popup;

import biz.freshcode.learn.gwtp.client.builder.gxt.DialogBuilder;
import biz.freshcode.learn.gwtp.client.popup.reuse.WindowPopupImpl;
import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.sencha.gxt.widget.core.client.Dialog;

public class GxtPopup extends PresenterWidget<GxtPopup.View> {
    @Inject
    public GxtPopup(EventBus eventBus, View view) {
        super(eventBus, view);
    }

    public void setModal(boolean modal) {
        Dialog dlg = (Dialog) getView().asWidget();
        dlg.setModal(modal);
    }

    public static class View extends WindowPopupImpl {
        @Inject
        public View(EventBus eventBus) {
            super(eventBus);
            initWidget(new DialogBuilder()
                    .add(new HTML("<p>Dialog!</p>"))
                    .dialog);
        }
    }
}
