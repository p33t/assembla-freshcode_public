package biz.freshcode.learn.gwt_bootstrap.client.grids;

import biz.freshcode.learn.gwt_bootstrap.client.boot.PlaceToken;
import biz.freshcode.learn.gwt_bootstrap.client.boot.RootPresenter;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.ColumnBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.ContainerBuilder;
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

public class Grids extends Presenter<View, Grids.Proxy> {

    @Inject
    public Grids(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, RootPresenter.SLOT);
    }

    @ProxyCodeSplit
    @NameToken(PlaceToken.TOK_GRIDS)
    public interface Proxy extends ProxyPlace<Grids> {
    }

    public static class View extends ViewImpl {
        @Inject
        public View() {
            initWidget(
                    new ContainerBuilder()
                            .add(
                                    new RowBuilder()
                                            .add(new ColumnBuilder(ColumnSize.XS_12)
                                                    .add(p("Hello Grids"))
                                                    .column)
                                            .row)
                            .container
            );
        }
    }
}
