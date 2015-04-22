package biz.freshcode.learn.gwt2.mod2.client.spike.customdropdown;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class CustomDropDownSpike extends Presenter<CustomDropDownSpike.View, CustomDropDownSpike.Proxy> {
    public static final String TOKEN = "customDropDownSpike";

    @Inject
    public CustomDropDownSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends ProxyPlace<CustomDropDownSpike> {
    }

    public static class View extends ViewImpl {
        private final Cdd cdd;

        @Inject
        public View() {
            initWidget(new HorizontalLayoutContainerBuilder()
                    .add(cdd = new Cdd())
                    .horizontalLayoutContainer);
            cdd.addValueChangeHandler(new ValueChangeHandler<String>() {
                @Override
                public void onValueChange(ValueChangeEvent<String> event) {
                    GWT.log("Value change to " + event.getValue());
                }
            });
        }
    }
}
