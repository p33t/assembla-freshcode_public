package biz.freshcode.learn.gwt_bootstrap.client.graphic;

import biz.freshcode.learn.gwt_bootstrap.client.boot.PlaceToken;
import biz.freshcode.learn.gwt_bootstrap.client.boot.RootPresenter;
import biz.freshcode.learn.gwt_bootstrap.client.builder.com.google.gwt.user.client.ui.HTMLBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.ColumnBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.ContainerBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.ImageBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.RowBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.html.ParagraphBuilder;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.constants.*;
import org.gwtbootstrap3.client.ui.html.Text;

import static biz.freshcode.learn.gwt_bootstrap.client.boot.BootBundle.BOOT_BUNDLE;
import static biz.freshcode.learn.gwt_bootstrap.client.boot.BootBundle.BOOT_STYLE;
import static biz.freshcode.learn.gwt_bootstrap.client.builder.ParagraphSupport.p;

public class Graphic extends Presenter<View, Graphic.Proxy> {

    @Inject
    public Graphic(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, RootPresenter.SLOT);
    }

    @ProxyCodeSplit
    @NameToken(PlaceToken.TOK_GRAPHIC)
    public interface Proxy extends ProxyPlace<Graphic> {
    }

    public static class View extends ViewImpl {
        @Inject
        public View() {
            initWidget(
                    new ContainerBuilder()
                            .add(new RowBuilder()
                                    .add(new ColumnBuilder(ColumnSize.XS_12)
                                            .add(new Heading(HeadingSize.H1, "Graphic"))
                                            .column)
                                    .add(new ParagraphBuilder()
                                            .add(new ImageBuilder()
                                                    .addStyleName(BOOT_STYLE.lightBgnd())
                                                    .url(BOOT_BUNDLE.strategy8().getSafeUri())
                                                    .type(ImageType.ROUNDED)
                                                    .image)
                                            .add(new Text(" This is some text to go with the image"))
                                            .paragraph)
                                    .add(p("Image below saved from Inkscape with plain SVG.  'width' will only clip the image."))
                                    .add(new HTML("<object width='150' type='image/svg+xml' data='" + GWT.getModuleBaseForStaticFiles() + "media/plain.svg'>SVG not supported</object>"))

                                    .add(p("Image below saved from Inkscape with optimised SVG and the 'viewport' option.  Now 'width' works."))
                                    .add(new HTML("<object width='100' type='image/svg+xml' data='" + GWT.getModuleBaseForStaticFiles() + "media/optim.svg'>SVG not supported</object>"))

                                    .add(p("Image below exported as SVG from Animatron.com."))
                                    .add(new HTML("<object width='100' type='image/svg+xml' data='" + GWT.getModuleBaseForStaticFiles() + "media/optim.svg'>SVG not supported</object>"))

                                    .add(p("Now lets float it..."))
                                    .add(new HTMLBuilder("<object width='300' type='image/svg+xml' data='" + GWT.getModuleBaseForStaticFiles() + "media/animated.svg'>SVG not supported</object>")
                                            .addStyleName(Pull.LEFT.getCssName())
                                            .hTML)
                                    .add(p("Hopefully this text is right next to the image."))
                                    .row)
                            .add(new RowBuilder()
                                    .addStyleName(BOOT_STYLE.graphicBgnd())
                                    .add(new ColumnBuilder(ColumnSize.SM_4)
                                            .addOffset(ColumnOffset.SM_4)
                                            .add(new ParagraphBuilder()
                                                    .add(new ImageBuilder()
                                                            .addStyleName(BOOT_STYLE.lightBgnd())
                                                            .url(BOOT_BUNDLE.strategy8().getSafeUri())
                                                            .type(ImageType.ROUNDED)
                                                            .image)
                                                    .add(new Text(" Looks like backgrounds don't resize."))
                                                    .paragraph)
                                            .column)
                                    .row)
                            .container);
        }
    }
}
