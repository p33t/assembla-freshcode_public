package biz.freshcode.learn.gwt2.mod2.client.spike.gridgraphic;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.DialogBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.draw.DrawComponentBuilder;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import biz.freshcode.learn.gwt2.mod2.client.spike.gridgraphic.reuse.StretchDrawComponent;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.sencha.gxt.chart.client.chart.series.Primitives;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class GridGraphicSpike extends Presenter<GridGraphicSpike.View, GridGraphicSpike.Proxy> {
    public static final String TOKEN = "gridGraphicSpike";

    @Inject
    public GridGraphicSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends ProxyPlace<GridGraphicSpike> {
    }

    public static class View extends ViewImpl {

        @Inject
        public View() {
            initWidget(new HorizontalLayoutContainerBuilder()
                    .add(new TextButton("Go", new SelectEvent.SelectHandler() {
                        @Override
                        public void onSelect(SelectEvent event) {
                            go();
                        }
                    }))
                    .horizontalLayoutContainer);
        }

        private void go() {
            new DialogBuilder()
                    .modal(true)
                    .title("Experiment")
                    .width(600)
                    .height(400)
                    .add(new DrawComponentBuilder(new StretchDrawComponent())
                            // NOTE: dimensions are expressed as ratio of container.
                            .addSprite(Primitives.triangle(.5, .5, .2))
                            .drawComponent)
                    .dialog.show();
        }
    }
}
