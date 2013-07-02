package biz.freshcode.learn.gwtp.client.popup;

import biz.freshcode.learn.gwtp.client.boot.Root;
import biz.freshcode.learn.gwtp.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.widget.client.TextButton;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class PopupDemo extends Presenter<PopupDemo.View, PopupDemo.Proxy> {
    public static final String TOKEN = "popupDemo";

    @Inject
    private ThePopup thePopup;

    @Inject
    public PopupDemo(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
        view.getBtnPopup().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                popup();
            }
        });
    }

    private void popup() {
        addToPopupSlot(thePopup);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends ProxyPlace<PopupDemo> {
    }

    public static class View extends ViewImpl {
        private final TextButton btnPopup;

        @Inject
        View() {
            initWidget(new VerticalLayoutContainerBuilder()
                    .add(btnPopup = new TextButton("Popup"))
                    .verticalLayoutContainer);
        }

        TextButton getBtnPopup() {
            return btnPopup;
        }
    }
}
