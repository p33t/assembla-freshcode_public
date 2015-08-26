package biz.freshcode.learn.gwt_bootstrap.client.timed;

import biz.freshcode.learn.gwt_bootstrap.client.boot.RootPresenter;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.ColumnBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.ImageBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.RowBuilder;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import org.gwtbootstrap3.client.ui.Image;
import org.gwtbootstrap3.client.ui.constants.ColumnSize;
import org.gwtbootstrap3.client.ui.constants.ImageType;
import org.gwtbootstrap3.client.ui.html.Paragraph;
import org.gwtbootstrap3.extras.animate.client.ui.constants.Animation;

import static biz.freshcode.learn.gwt_bootstrap.client.boot.BootBundle.BOOT_BUNDLE;
import static biz.freshcode.learn.gwt_bootstrap.client.boot.BootBundle.BOOT_STYLE;
import static biz.freshcode.learn.gwt_bootstrap.client.boot.PlaceToken.TOK_TIMED;

public class Timed extends Presenter<Timed.View, Timed.Proxy> {

    @Inject
    public Timed(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, RootPresenter.SLOT);
    }

    @Override
    protected void onReset() {
        super.onReset();
        GWT.log("Timed is being reset");
        Image img = getView().getImgMid();
        img.removeStyleName(Animation.FADE_IN.getCssName());
        img.getElement().getStyle().setOpacity(0);

        Scheduler.get().scheduleFixedDelay(new Scheduler.RepeatingCommand() {
            @Override
            public boolean execute() {
                if (isVisible()) {
                    GWT.log("Adding animation");
                    Image img = getView().getImgMid();
                    img.addStyleName(Animation.FADE_IN.getCssName());
                }
                else {
                    GWT.log("Not Visible");
                }
                return false;
            }
        }, 2000);
    }

    @ProxyCodeSplit
    @NameToken(TOK_TIMED)
    public interface Proxy extends ProxyPlace<Timed> {
    }

    public static class View extends ViewImpl {
        private final Image imgMid;

        @Inject
        public View() {
            initWidget(new RowBuilder()
                    .add(new ColumnBuilder(ColumnSize.XS_4)
                            .add(new Paragraph("An image will appear in 2 secs..."))
                            .column)
                    .add(new ColumnBuilder(ColumnSize.XS_4)
                            .add(imgMid = new ImageBuilder()
                                    .addStyleName(BOOT_STYLE.lightBgnd())
                                    .url(BOOT_BUNDLE.strategy8().getSafeUri())
                                    .type(ImageType.ROUNDED)
                                    .image)
                            .column)
                    .row);
        }

        public Image getImgMid() {
            return imgMid;
        }
    }
}
