package biz.freshcode.learn.gwt_bootstrap.client.carousels;

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
import org.gwtbootstrap3.client.ui.html.Paragraph;

import static biz.freshcode.learn.gwt_bootstrap.client.boot.PlaceToken.TOK_HOME;
import static biz.freshcode.learn.gwt_bootstrap.client.builder.ParagraphSupport.p;

public class Carousels extends Presenter<View, Carousels.Proxy> {

    @Inject
    public Carousels(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, RootPresenter.SLOT);
    }

    @ProxyCodeSplit
    @NameToken(PlaceToken.TOK_CAROUSELS)
    public interface Proxy extends ProxyPlace<Carousels> {
    }

    public static class View extends ViewImpl {
        @Inject
        public View() {
            initWidget(new RowBuilder()
                    .add(new ColumnBuilder(ColumnSize.XS_12)
                            .add(new Paragraph("Carousels Baby!"))
                            .column)
                    .row);
        }
    }
}
