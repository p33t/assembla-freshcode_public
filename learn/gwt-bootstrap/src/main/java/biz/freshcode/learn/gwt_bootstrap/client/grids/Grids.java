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
import org.gwtbootstrap3.client.ui.Column;
import org.gwtbootstrap3.client.ui.constants.*;
import org.gwtbootstrap3.client.ui.html.Paragraph;
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
                                    .add(heading("Plan & Manage Change"))
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
                                    // a slightly less nasty way to do vertical align
                                    .addStyleName(BOOT_STYLE.verticalAlignContents())
                                    .height("128px") // same as adjacent image
                                    .add(heading("Plan & Manage Change"))
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
                    // A better way to do vertically centered bullets with large image.  Too bad we can't to fixed col widths.
                    .add(new RowBuilder()
                            .add(new ColumnBuilder(SM_2)
                                    .hiddenOn(DeviceSize.XS)
                                    .column)
                            .add(imgCol())
                            .add(itemCol("Entry number one"))
                            .add(imgCol())
                            .add(itemCol("Entry number two"))
                            .row)
                    .add(new RowBuilder()
                            .add(new ColumnBuilder(SM_2)
                                    .hiddenOn(DeviceSize.XS)
                                    .column)
                            .add(imgCol())
                            .add(itemCol("Entry number thee"))
                            .add(imgCol())
                            .add(itemCol("Entry number four"))
                            .row)
                    .container);
        }

        private Column itemCol(String txt) {
            return new ColumnBuilder(XS_9, SM_3)
                    .addStyleName(BOOT_STYLE.verticalAlignContents())
                    .height("50px")
                    .add(new Paragraph(txt))
                    .column;
        }

        private Column imgCol() {
            return new ColumnBuilder(XS_3, SM_2)
                    .add(new ImageBuilder()
                            .responsive(true)
                            .addStyleName(BOOT_STYLE.noPadding())
                            .pull(Pull.RIGHT)
                            .url(BOOT_BUNDLE.logoSml().getSafeUri().asString())
                            .type(ImageType.CIRCLE)
                            .image)
                    .column;
        }

        private Paragraph heading(String txt) {
            return new ParagraphBuilder()
                    // A nastier way to do vertical align...
//                                            .addStyleName(BOOT_STYLE.verticalAlign98())
                    .addStyleName("h2")
                    .add(new Text(txt))
                    .paragraph;
        }

        private void item(String txt, Column col) {
            new ColumnBuilder(col)
                    .addStyleName(BOOT_STYLE.verticalAlignContents())
                    .height("50px")
                    .add(new ImageBuilder()
//                            .responsive(true)
                            .pull(Pull.LEFT)
                            .url(BOOT_BUNDLE.logoSml().getSafeUri().asString())
                            .type(ImageType.CIRCLE)
                            .image)
                    .add(new ParagraphBuilder()
                            .add(new Text(txt))
                            .paragraph)
            ;
        }
    }
}
