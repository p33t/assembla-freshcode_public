package biz.freshcode.learn.gwt_bootstrap.client.carousels;

import biz.freshcode.learn.gwt_bootstrap.client.boot.PlaceToken;
import biz.freshcode.learn.gwt_bootstrap.client.boot.RootPresenter;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.*;
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
import org.gwtbootstrap3.client.ui.Carousel;
import org.gwtbootstrap3.client.ui.CarouselIndicator;
import org.gwtbootstrap3.client.ui.Image;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.ImageType;

import static biz.freshcode.learn.gwt_bootstrap.client.boot.BootBundle.BOOT_BUNDLE;
import static biz.freshcode.learn.gwt_bootstrap.client.boot.BootBundle.BOOT_STYLE;

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
            Carousel csel = new Carousel();
//            NOTE: Still need 'slide' class.
//            csel.removeStyleName("slide");
            initWidget(new CarouselBuilder(csel)
                    .interval(3000)
                            .height("300px")
                            // Want 'fade' instead.  This uses override-style.css
                    .addStyleName("carousel-fade")
                    .add(new CarouselIndicatorsBuilder()
                            .add(new CarouselIndicatorBuilder()
                                    .dataTargetWidget(csel)
                                    .dataSlideTo("0")
                                    .active(true)
                                    .carouselIndicator)
                            .add(indicator(csel, "1"))
                            .add(indicator(csel, "2"))
                            .add(indicator(csel, "3"))
                            .carouselIndicators)
                    .add(new CarouselInnerBuilder()
                            .add(new CarouselSlideBuilder()
                                    .active(true)
                                    .add(image(ImageType.ROUNDED))
                                    .carouselSlide)
                            .add(new CarouselSlideBuilder()
                                    .add(image(ImageType.CIRCLE))
                                    .carouselSlide)
                            .add(new CarouselSlideBuilder()
                                    .add(new HTML("<object height='300px' type='image/svg+xml' data='" + GWT.getModuleBaseForStaticFiles() + "media/animated.svg'>SVG not supported</object>"))
                                    .carouselSlide)
                            .add(new CarouselSlideBuilder()
                                    .add(new HTML("<object height='300px' type='image/svg+xml' data='" + GWT.getModuleBaseForStaticFiles() + "media/composite.css.optim.svg'>SVG not supported</object>"))
                                    .carouselSlide)
                            .carouselInner)
                    .add(new CarouselControlBuilder()
                            .dataTargetWidget(csel)
                            .prev(true)
                            .iconType(IconType.ANGLE_LEFT)
                            .carouselControl)
                    .add(new CarouselControlBuilder()
                            .dataTargetWidget(csel)
                                    // Nix the gradient fill
                            .addStyleName(BOOT_STYLE.noBgndImg())
                            .next(true)
                            .iconType(IconType.ANGLE_RIGHT)
                            .carouselControl)
                    .carousel);
        }

        private CarouselIndicator indicator(Carousel csel, String dataSlideTo) {
            return new CarouselIndicatorBuilder()
                    .dataTargetWidget(csel)
                    .dataSlideTo(dataSlideTo)
                    .carouselIndicator;
        }

        private Image image(ImageType imageType) {
            return new ImageBuilder()
                    .addStyleName("img-responsive")
                    .addStyleName("center-block")
                    .addStyleName(BOOT_STYLE.lightBgnd())
                    .url(BOOT_BUNDLE.strategy8().getSafeUri())
                    .type(imageType)
                    .image;
        }
    }
}
