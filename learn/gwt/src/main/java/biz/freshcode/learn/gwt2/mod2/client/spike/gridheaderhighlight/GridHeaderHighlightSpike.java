package biz.freshcode.learn.gwt2.mod2.client.spike.gridheaderhighlight;

import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;

/**
 * Want to change the style of a column header.  EG: Switch bold on/off.
 */
public class GridHeaderHighlightSpike extends Presenter<GridHeaderHighlightSpike.View, GridHeaderHighlightSpike.Proxy> {
    public static final String TOKEN = "gridHeaderHighlight";

    @Inject
    public GridHeaderHighlightSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<GridHeaderHighlightSpike> {
    }

    public static class View extends ViewImpl {

        @Inject
        public View() {
            initWidget(new HTML("Hi"));
        }
    }
}
