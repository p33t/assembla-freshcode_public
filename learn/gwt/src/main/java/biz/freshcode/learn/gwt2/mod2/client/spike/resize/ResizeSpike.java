package biz.freshcode.learn.gwt2.mod2.client.spike.resize;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.form.FieldLabelBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.form.TextAreaBuilder;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

/**
 * Trying to figure out why a text area is not resizing automatically.
 * Turns out the width on a FieldLabel and TextArea should not be used.
 * The VerticalLayoutData of width 1.0 ensures the contents are resized.
 */
public class ResizeSpike extends Presenter<View, ResizeSpike.Proxy> {
    public static final String TOKEN = "resize";

    @Inject
    public ResizeSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }


    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<ResizeSpike> {
    }

    public static class View extends ViewImpl {
        @Inject
        public View() {
            initWidget(new VerticalLayoutContainerBuilder()
                    .scrollMode(ScrollSupport.ScrollMode.AUTOY)
                    .add(new FieldLabelBuilder()
                            .text("Label")
                            .widget(new TextAreaBuilder()
                                    .textArea)
                            .fieldLabel, new VerticalLayoutData(1.0, 150))
                    .verticalLayoutContainer);
        }
    }
}
