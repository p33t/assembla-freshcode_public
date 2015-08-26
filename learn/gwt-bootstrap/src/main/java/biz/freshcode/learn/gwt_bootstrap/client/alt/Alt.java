package biz.freshcode.learn.gwt_bootstrap.client.alt;

import biz.freshcode.learn.gwt_bootstrap.client.boot.PlaceToken;
import biz.freshcode.learn.gwt_bootstrap.client.boot.RootPresenter;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.ColumnBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.JumbotronBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.RowBuilder;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import org.gwtbootstrap3.client.ui.constants.ColumnSize;

import static biz.freshcode.learn.gwt_bootstrap.client.boot.PlaceToken.TOK_HOME;
import static biz.freshcode.learn.gwt_bootstrap.client.builder.ParagraphSupport.p;

public class Alt extends Presenter<View, Alt.Proxy> {

    @Inject
    public Alt(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, RootPresenter.SLOT);
    }

    @ProxyCodeSplit
    @NameToken(PlaceToken.TOK_ALT)
    public interface Proxy extends ProxyPlace<Alt> {
    }

    public static class View extends ViewImpl {
        @Inject
        public View() {
            initWidget(new RowBuilder()
                    .add(new ColumnBuilder(ColumnSize.XS_12)
                            .add(new JumbotronBuilder()
                                    .add(p("Hello Alt"))
                                    .add(p("Would you like to go "))
                                    .add(p("Home", TOK_HOME))
                                    .add(p("Note that entities and elems are passed as literals: &amp; <bruce>lee</bruce>"))
                                    .add(p("Trying"))
                                    .add(p("to"))
                                    .add(p("get"))
                                    .add(p("a"))
                                    .add(p("very"))
                                    .add(p("long"))
                                    .add(p("page"))
                                    .add(p("to"))
                                    .add(p("activate"))
                                    .add(p("scroll"))
                                    .add(p("effects"))
                                    .add(p("..."))
                                    .add(p("please."))
                                    .jumbotron)
                            .column)
                    .row);
        }
    }
}
