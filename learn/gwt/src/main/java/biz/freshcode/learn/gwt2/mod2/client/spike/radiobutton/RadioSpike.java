package biz.freshcode.learn.gwt2.mod2.client.spike.radiobutton;

import biz.freshcode.learn.gwt2.common.client.builder.gwt.HorizontalPanelBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.ContentPanelBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.form.FieldLabelBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.form.RadioBuilder;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HasValue;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.info.Info;

/**
 * Why are radio button events not working?
 * I must have been using them wrong.
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
        private final ToggleGroup group = new ToggleGroup();

        @Inject
        public View() {
            initWidget(new ContentPanelBuilder()
                    .widget(new VerticalLayoutContainerBuilder()
                            .add(new FieldLabelBuilder()
                                    .text("Radio")
                                    .widget(new HorizontalPanelBuilder()
                                            .add(createRadio("Val1"))
                                            .add(createRadio("Val2"))
                                            .horizontalPanel)
                                    .fieldLabel)
                            .verticalLayoutContainer)
                    .contentPanel);
            group.addValueChangeHandler(new ValueChangeHandler<HasValue<Boolean>>() {
                @Override
                public void onValueChange(ValueChangeEvent<HasValue<Boolean>> event) {
                    Radio r = (Radio) event.getValue();
                    Info.display("Group Event", "Value change to " + r.getItemId());
                }
            });
        }

        private Radio createRadio(String lbl) {
            Radio r = new RadioBuilder()
//                    .name(NAME)
                    .boxLabel(lbl)
                    .itemId("Item: " + lbl)
                    .radio;
            group.add(r);
            return r;
        }
    }
}
