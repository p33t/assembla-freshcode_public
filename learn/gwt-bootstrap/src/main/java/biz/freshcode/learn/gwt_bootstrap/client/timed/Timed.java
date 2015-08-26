package biz.freshcode.learn.gwt_bootstrap.client.timed;

import biz.freshcode.learn.gwt_bootstrap.client.boot.PlaceToken;
import biz.freshcode.learn.gwt_bootstrap.client.boot.RootPresenter;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.ColumnBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.ImageBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.RowBuilder;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import org.gwtbootstrap3.client.ui.constants.ColumnSize;
import org.gwtbootstrap3.client.ui.constants.ImageType;

import static biz.freshcode.learn.gwt_bootstrap.client.boot.BootBundle.BOOT_BUNDLE;
import static biz.freshcode.learn.gwt_bootstrap.client.boot.BootBundle.BOOT_STYLE;

public class Timed extends Presenter<View, Timed.Proxy> {
    @Inject
    public Timed(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, RootPresenter.SLOT);
    }

    @ProxyCodeSplit
    @NameToken(PlaceToken.TOK_TIMED)
    public interface Proxy extends ProxyPlace<Timed> {
    }

    public static class View extends ViewImpl {
        private final SimplePanel pnlMid;
        private final SimplePanel pnlRight;

        @Inject
        public View() {
            initWidget(new RowBuilder()
                    .add(new ColumnBuilder(ColumnSize.XS_4)
                            .add(new ImageBuilder()
                                    .addStyleName(BOOT_STYLE.lightBgnd())
                                    .url(BOOT_BUNDLE.strategy8().getSafeUri())
                                    .type(ImageType.ROUNDED)
                                    .image)
                            .column)
                    .add(new ColumnBuilder(ColumnSize.XS_4)
                            .add(pnlMid = new SimplePanel())
                            .column)
                    .add(new ColumnBuilder(ColumnSize.XS_4)
                            .add(pnlRight = new SimplePanel())
                            .column)
                    .row);
        }

        public SimplePanel getPnlMid() {
            return pnlMid;
        }

        public SimplePanel getPnlRight() {
            return pnlRight;
        }
    }
}
