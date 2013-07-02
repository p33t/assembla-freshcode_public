package biz.freshcode.learn.gwtp.client.popup;

import biz.freshcode.learn.gwtp.client.boot.Root;
import biz.freshcode.learn.gwtp.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwtp.client.builder.gxt.form.FieldLabelBuilder;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.widget.client.TextButton;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.sencha.gxt.widget.core.client.form.CheckBox;

public class PopupDemo extends Presenter<PopupDemo.View, PopupDemo.Proxy> {
    public static final String TOKEN = "popupDemo";

    @Inject
    private GwtPopup gwtPopup;

    @Inject
    private GxtPopup gxtPopup;

    @Inject
    public PopupDemo(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
        view.getBtnPopup().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                popup(gwtPopup);
            }
        });
        view.getBtnGxtPopup().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                boolean modal = getView().isModal();
                gxtPopup.setModal(modal);
                popup(gxtPopup);
            }
        });
    }

    private void popup(PresenterWidget<? extends PopupView> popup) {
        boolean center = getView().isCentered();
        addToPopupSlot(popup, center);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends ProxyPlace<PopupDemo> {
    }

    public static class View extends ViewImpl {
        private final TextButton btnPopup;
        private final TextButton btnGxtPopup;
        private final CheckBox chkModal;
        private final CheckBox chkCenter;

        @Inject
        public View() {
            initWidget(new VerticalLayoutContainerBuilder()
                    .add(btnPopup = new TextButton("Gwt Popup"))
                    .add(btnGxtPopup = new TextButton("Gxt Popup"))
                    .add(new FieldLabelBuilder()
                            .text("Modal (gxt)")
                            .add(chkModal = new CheckBox())
                            .fieldLabel)
                    .add(new FieldLabelBuilder()
                            .text("Center")
                            .add(chkCenter = new CheckBox())
                            .fieldLabel)
                    .verticalLayoutContainer);
        }

        public TextButton getBtnPopup() {
            return btnPopup;
        }

        public TextButton getBtnGxtPopup() {
            return btnGxtPopup;
        }

        public boolean isModal() {
            return chkModal.getValue();
        }

        public boolean isCentered() {
            return chkCenter.getValue();
        }
    }
}
