package biz.freshcode.learn.gwt2.mod2.client.spike.radiobutton;

import biz.freshcode.learn.gwt2.common.client.builder.gwt.HorizontalPanelBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.ContentPanelBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.form.FieldLabelBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.form.RadioBuilder;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;

/**
 * Why are radio button events not working?
 */
public class RadioSpike extends Presenter<RadioSpike.View, RadioSpike.Proxy> {
    public static final String TOKEN = "radio";

    @Inject
    public RadioSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<RadioSpike> {
    }

    public static class View extends ViewImpl {
        public static final String NAME = "radio-spike-name";

        @Inject
        public View() {
            initWidget(new ContentPanelBuilder()
                    .add(new FieldLabelBuilder()
                            .text("Radio")
                            .widget(new HorizontalPanelBuilder()
                                    .add(new RadioBuilder()
                                            .name(NAME)
                                            .boxLabel("Val1")
                                            .radio)
                                    .add(new RadioBuilder()
                                            .name(NAME)
                                            .boxLabel("Val2")
                                            .radio)
                                    .horizontalPanel)
                            .fieldLabel)
                    .contentPanel);
        }

    }
}
