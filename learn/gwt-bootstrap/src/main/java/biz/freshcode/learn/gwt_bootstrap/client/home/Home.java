package biz.freshcode.learn.gwt_bootstrap.client.home;

import biz.freshcode.learn.gwt_bootstrap.client.alt.Alt;
import biz.freshcode.learn.gwt_bootstrap.client.boot.RootPresenter;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.ColumnBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.RowBuilder;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.constants.ColumnSize;
import org.gwtbootstrap3.client.ui.constants.HeadingSize;
import org.gwtbootstrap3.client.ui.html.Paragraph;

public class Home extends Presenter<View, Home.Proxy> {
    public static final String TOKEN = "home";

    @Inject
    public Home(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, RootPresenter.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends ProxyPlace<Home> {
    }

    public static class View extends ViewImpl {
        @Inject
        public View() {
            initWidget(
                    new RowBuilder()
                            .add(new ColumnBuilder(ColumnSize.XS_12)
                                    // NOTE: H1 seems to resist getting stuck under the nav bar (like paragraph)
                                    .add(new Heading(HeadingSize.H1, "Hello World"))
                                    // TODO: There has to be a better way.
                                    .add(new Paragraph("Another way to get to the " + a("Alt Page", "#" + Alt.TOKEN) + "."))
                                    .column)
                            .row
            );
        }

        public static String a(String text, String href) {
            return "<a href='" + href + "'>" + text + "</a>";
        }
    }
}
