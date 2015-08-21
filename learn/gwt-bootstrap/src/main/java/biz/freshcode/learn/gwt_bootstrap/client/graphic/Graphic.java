package biz.freshcode.learn.gwt_bootstrap.client.graphic;

import biz.freshcode.learn.gwt_bootstrap.client.boot.PlaceToken;
import biz.freshcode.learn.gwt_bootstrap.client.boot.RootPresenter;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.ColumnBuilder;
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
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.constants.ColumnSize;
import org.gwtbootstrap3.client.ui.constants.HeadingSize;
import org.gwtbootstrap3.client.ui.constants.ImageType;
import org.gwtbootstrap3.client.ui.html.Text;

import static biz.freshcode.learn.gwt_bootstrap.client.boot.BootBundle.BOOT_BUNDLE;
import static biz.freshcode.learn.gwt_bootstrap.client.boot.BootBundle.BOOT_STYLE;

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
                    new RowBuilder()
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
                            .row);
        }
    }
}
