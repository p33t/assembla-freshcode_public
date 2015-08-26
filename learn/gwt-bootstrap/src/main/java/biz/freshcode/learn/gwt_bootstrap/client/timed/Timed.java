package biz.freshcode.learn.gwt_bootstrap.client.timed;

import biz.freshcode.learn.gwt_bootstrap.client.boot.RootPresenter;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.ColumnBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.ImageBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.RowBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.html.ParagraphBuilder;
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
import org.gwtbootstrap3.client.ui.constants.HeadingSize;
import org.gwtbootstrap3.client.ui.constants.ImageType;
import org.gwtbootstrap3.client.ui.html.Paragraph;
import org.gwtbootstrap3.client.ui.html.Text;
import org.gwtbootstrap3.extras.animate.client.ui.constants.Animation;

import static biz.freshcode.learn.gwt_bootstrap.client.boot.BootBundle.BOOT_BUNDLE;
import static biz.freshcode.learn.gwt_bootstrap.client.boot.BootBundle.BOOT_STYLE;
import static biz.freshcode.learn.gwt_bootstrap.client.boot.PlaceToken.TOK_TIMED;

public class Timed extends Presenter<Timed.View, Timed.Proxy> {

    public static final String ANIMATION_CLASS = Animation.FADE_IN.getCssName();

    @Inject
    public Timed(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, RootPresenter.SLOT);
    }

    @Override
    protected void onReset() {
        super.onReset();
        GWT.log("Timed is being reset");
        Image img = getView().getImgMid();
        img.removeStyleName(ANIMATION_CLASS);
        img.getElement().getStyle().setOpacity(0);
        Paragraph par = getView().getParRight();
        par.removeStyleName(ANIMATION_CLASS);
        par.getElement().getStyle().setOpacity(0);

        Scheduler.get().scheduleFixedDelay(new Scheduler.RepeatingCommand() {
            @Override
            public boolean execute() {
                if (isVisible()) {
                    GWT.log("Adding animation");
                    getView().getImgMid().addStyleName(ANIMATION_CLASS);
                    getView().getParRight().addStyleName(ANIMATION_CLASS);
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
        private final Paragraph parRight;

        @Inject
        public View() {
            initWidget(new RowBuilder()
                    .add(new ColumnBuilder(ColumnSize.XS_4)
                            .add(new Paragraph("An image and paragraph will appear in 2 secs..."))
                            .column)
                    .add(new ColumnBuilder(ColumnSize.XS_4)
                            .add(imgMid = new ImageBuilder()
                                    .addStyleName(BOOT_STYLE.lightBgnd())
                                    .url(BOOT_BUNDLE.strategy8().getSafeUri())
                                    .type(ImageType.ROUNDED)
                                    .image)
                            .column)
                    .add(new ColumnBuilder(ColumnSize.XS_4)
                            .add(parRight = new ParagraphBuilder()
                                    .addStyleName(HeadingSize.H1.name())
                                    .add(new Text("Paragraph"))
                                    .paragraph)
                            .column)
                    .row);
        }

        public Image getImgMid() {
            return imgMid;
        }

        public Paragraph getParRight() {
            return parRight;
        }
    }
}
