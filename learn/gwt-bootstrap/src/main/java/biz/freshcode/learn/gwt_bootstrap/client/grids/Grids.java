package biz.freshcode.learn.gwt_bootstrap.client.grids;

import biz.freshcode.learn.gwt_bootstrap.client.boot.PlaceToken;
import biz.freshcode.learn.gwt_bootstrap.client.boot.RootPresenter;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.ColumnBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.ContainerBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.ImageBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.RowBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.html.ParagraphBuilder;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import org.gwtbootstrap3.client.ui.constants.ColumnOffset;
import org.gwtbootstrap3.client.ui.constants.DeviceSize;
import org.gwtbootstrap3.client.ui.constants.ImageType;
import org.gwtbootstrap3.client.ui.html.Text;

import static biz.freshcode.learn.gwt_bootstrap.client.boot.BootBundle.BOOT_BUNDLE;
import static biz.freshcode.learn.gwt_bootstrap.client.boot.BootBundle.BOOT_STYLE;
import static org.gwtbootstrap3.client.ui.constants.ColumnSize.*;

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
            initWidget(new ContainerBuilder()
                    .add(new RowBuilder()
                            .add(new ColumnBuilder(XS_12)
                                    .visibleOn(DeviceSize.XS)
                                    .add(new ImageBuilder()
                                            .addStyleName(BOOT_STYLE.lightBgnd())
                                            .url(BOOT_BUNDLE.strategy8().getSafeUri())
                                            .type(ImageType.ROUNDED)
                                            .image)
                                    .column)
                            .row)
                    .add(new RowBuilder()
                            .add(new ColumnBuilder(SM_3)
                                    .hiddenOn(DeviceSize.XS)
                                    .add(new ImageBuilder()
                                            .addStyleName(BOOT_STYLE.lightBgnd())
                                            .url(BOOT_BUNDLE.strategy8().getSafeUri())
                                            .type(ImageType.ROUNDED)
                                            .image)
                                    .column)
                            .add(new ColumnBuilder(XS_12, SM_9)
                                    .add(new ParagraphBuilder()
                                            .addStyleName("h2")
                                            .add(new Text("Plan & Manage Change"))
                                            .paragraph)
                                    .column)
                            .row)
                    .add(new RowBuilder()
                            .add(new ColumnBuilder(SM_4, XS_12)
                                    .offset(ColumnOffset.SM_3, ColumnOffset.XS_0)
                                    .add(new ParagraphBuilder()
                                            .add(new ImageBuilder()
                                                    .url(BOOT_BUNDLE.pin().getSafeUri())
                                                    .type(ImageType.ROUNDED)
                                                    .image)
                                            .add(new Text(" Entry number one"))
                                            .addStyleName("h4")
                                            .paragraph)
                                    .column)
                            .add(new ColumnBuilder(SM_5, XS_12)
                                    .add(new ParagraphBuilder()
                                            .add(new ImageBuilder()
                                                    .url(BOOT_BUNDLE.pin().getSafeUri())
                                                    .type(ImageType.ROUNDED)
                                                    .image)
                                            .add(new Text(" Entry number two"))
                                            .addStyleName("h4")
                                            .paragraph)
                                    .column)
                            .row)
                    .container);
        }
    }
}
