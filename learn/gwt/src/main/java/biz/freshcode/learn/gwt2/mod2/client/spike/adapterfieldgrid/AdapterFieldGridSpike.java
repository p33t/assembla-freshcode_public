package biz.freshcode.learn.gwt2.mod2.client.spike.adapterfieldgrid;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.form.FieldLabelBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.toolbar.ToolBarBuilder;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class AdapterFieldGridSpike extends Presenter<AdapterFieldGridSpike.View, AdapterFieldGridSpike.Proxy> {
    public static final String TOKEN = "adapterFieldGrid";

    @Inject
    public AdapterFieldGridSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
        view.getClearInvalid().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                getView().getGrid().clearInvalid();
            }
        });
        view.getMarkInvalid().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                getView().getGrid().markInvalid("Naughty!");
            }
        });
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<AdapterFieldGridSpike> {
    }

    public static class View extends ViewImpl {
        private final TextButton markInvalid;
        private final TextButton clearInvalid;
        private final GridField grid;

        @Inject
        public View(GridField grid) {
            initWidget(new VerticalLayoutContainerBuilder()
                    .scrollMode(ScrollSupport.ScrollMode.AUTOY)
                    .add(new ToolBarBuilder()
                            .add(markInvalid = new TextButton("Mark Invalid"))
                            .add(clearInvalid = new TextButton("Clear Invalid"))
                            .toolBar, new VerticalLayoutData(1.0, 30))
                    .add(new FieldLabelBuilder()
                            .text("Label")
                            .widget(this.grid = grid)
                            .fieldLabel, new VerticalLayoutData(1.0, 150))

                    .verticalLayoutContainer);
        }

        public TextButton getClearInvalid() {
            return clearInvalid;
        }

        public GridField getGrid() {
            return grid;
        }

        public TextButton getMarkInvalid() {
            return markInvalid;
        }
    }
}
