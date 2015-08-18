package biz.freshcode.learn.gwt_bootstrap.client.home;

import biz.freshcode.learn.gwt_bootstrap.client.boot.PlaceToken;
import biz.freshcode.learn.gwt_bootstrap.client.boot.RootPresenter;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.AnchorBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.ColumnBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.RowBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.html.ParagraphBuilder;
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
import org.gwtbootstrap3.client.ui.html.Text;

import static biz.freshcode.learn.gwt_bootstrap.client.boot.PlaceToken.TOK_ALT;

public class Home extends Presenter<View, Home.Proxy> {

    @Inject
    public Home(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, RootPresenter.SLOT);
    }

    @ProxyStandard
    @NameToken(PlaceToken.TOK_HOME)
    public interface Proxy extends ProxyPlace<Home> {
    }

    public static class View extends ViewImpl {
        @Inject
        public View() {
            initWidget(
                    new RowBuilder()
                            .add(new ColumnBuilder(ColumnSize.XS_12)
                                    .add(new Heading(HeadingSize.H1, "Hello World"))
                                    .add(new ParagraphBuilder()
                                            .add(new Text("Another way to get to the "))
                                            .add(new AnchorBuilder()
                                                    .text("Alt Page")
                                                    .targetHistoryToken(TOK_ALT)
                                                    .anchor)
                                            .add(new Text("."))
                                            .paragraph)
                                    .column)
                            .row
            );
        }
    }
}
