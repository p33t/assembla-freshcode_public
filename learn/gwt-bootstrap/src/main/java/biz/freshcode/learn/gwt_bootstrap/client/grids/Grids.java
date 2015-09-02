package biz.freshcode.learn.gwt_bootstrap.client.grids;

import biz.freshcode.learn.gwt_bootstrap.client.boot.PlaceToken;
import biz.freshcode.learn.gwt_bootstrap.client.boot.RootPresenter;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.*;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.html.ParagraphBuilder;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import org.gwtbootstrap3.client.ui.Column;
import org.gwtbootstrap3.client.ui.MediaList;
import org.gwtbootstrap3.client.ui.constants.*;
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
                            .visibleOn(DeviceSize.XS)
                            .add(new ColumnBuilder(XS_12)
                                    .add(new ImageBuilder()
                                            .addStyleName(BOOT_STYLE.lightBgnd())
                                            .url(BOOT_BUNDLE.strategy8().getSafeUri())
                                            .type(ImageType.ROUNDED)
                                            .image)
                                    .add(new ParagraphBuilder()
                                            .addStyleName("h2")
                                            .add(new Text("Plan & Manage Change"))
                                            .paragraph)
                                    .column)
                            .row)
                    .add(new RowBuilder()
                            .hiddenOn(DeviceSize.XS)
                            .add(new ColumnBuilder(SM_3)
                                    .addStyleName(Alignment.RIGHT.getCssName())
                                    .add(new ImageBuilder()
                                            .addStyleName(BOOT_STYLE.lightBgnd())
                                            .url(BOOT_BUNDLE.strategy8().getSafeUri())
                                            .type(ImageType.ROUNDED)
                                            .image)
                                    .column)
                            .add(new ColumnBuilder(XS_12, SM_9)
                                    .add(new ParagraphBuilder()
                                            // Ugh... there isn't a pretty way to vertically center
                                            .addStyleName(BOOT_STYLE.verticalAlign98())
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
                                            .add(new Text(" Entry number one (bad wrap)"))
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
                            // NOTE: These don't have to be on different rows... it depends on what wrap behaviour is desired.
                    .add(new RowBuilder()
                            .add(leftCol("Entry number one (nice wrap)"))
                            .add(rightCol("Entry number two"))
                            .row)
                    .add(new RowBuilder()
                            .add(leftCol("Entry number three"))
                            .add(rightCol("Entry number four"))
                            .row)
                    .add(new RowBuilder()
                            .add(leftCol("Entry number five"))
                            .row)
                    .container);
        }

        private Column rightCol(String txt) {
            return new ColumnBuilder(SM_5, XS_12)
                    .add(mediaItem(txt))
                    .column;
        }

        private Column leftCol(String txt) {
            return new ColumnBuilder(SM_4, XS_12)
                    .offset(ColumnOffset.SM_3, ColumnOffset.XS_0)
                    .add(mediaItem(txt))
                    .column;
        }

        private MediaList mediaItem(String txt) {
            return new MediaListBuilder()
                    .add(new ListItemBuilder()
                            .add(new ImageAnchorBuilder()
                                    .asMediaObject(true)
                                    .pull(Pull.LEFT)
                                    .url(BOOT_BUNDLE.pin().getSafeUri().asString())
                                    .type(ImageType.ROUNDED)
                                    .imageAnchor)
                            .add(new MediaBodyBuilder()
                                    .add(new ParagraphBuilder()
                                            .add(new Text(txt))
                                            .paragraph)
                                    .mediaBody)
                            .listItem)
                    .mediaList;
        }
    }
}
