package biz.freshcode.learn.gwt2.mod2.client.bug;

import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import static com.google.gwt.safehtml.shared.SafeHtmlUtils.fromString;

public class ButtonBarFramedPanelBug extends Presenter<ButtonBarFramedPanelBug.View, ButtonBarFramedPanelBug.Proxy> {
    public static final String TOKEN = "buttonBarFramedPanelBug";

    @Inject
    public ButtonBarFramedPanelBug(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<ButtonBarFramedPanelBug> {
    }

    public static class View extends ViewImpl {

        @Inject
        public View() {
            BorderLayoutContainer blc = new BorderLayoutContainer();
            blc.setNorthWidget(
                    cp(new HtmlLayoutContainer(fromString("Button bar on a framed panel doesn't display properly.  " +
                            "Bottom is truncated."))),
                    borderData(0.3)
            );

            FramedPanel fp = new FramedPanel();
            fp.addButton(new TextButton("Hello"));
            fp.addButton(new TextButton("World"));
            blc.setWestWidget(
                    cp(fp),
                    borderData(0.4)
            );

            blc.setCenterWidget(cp(new HtmlLayoutContainer(fromString("Center"))));

            ToolBar status = new ToolBar();
            status.add(new FillToolItem());
            status.add(new LabelToolItem("Status Bar"));
            status.add(new FillToolItem());
            blc.setSouthWidget(status, new BorderLayoutContainer.BorderLayoutData(30));

            initWidget(blc);
        }

        private BorderLayoutContainer.BorderLayoutData borderData(double size) {
            BorderLayoutContainer.BorderLayoutData d = new BorderLayoutContainer.BorderLayoutData(size);
            d.setSplit(true);
            d.setMaxSize(10000);
            d.setMargins(new Margins(3));
            return d;
        }

        private IsWidget cp(IsWidget w) {
            ContentPanel p = new ContentPanel();
            p.add(w);
            p.setHeaderVisible(false);
            return p;
        }
    }
}
