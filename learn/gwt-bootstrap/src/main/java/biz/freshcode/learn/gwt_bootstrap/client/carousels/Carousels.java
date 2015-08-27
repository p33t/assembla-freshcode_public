package biz.freshcode.learn.gwt_bootstrap.client.carousels;

import biz.freshcode.learn.gwt_bootstrap.client.boot.PlaceToken;
import biz.freshcode.learn.gwt_bootstrap.client.boot.RootPresenter;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.*;
import com.google.gwt.dom.client.Style;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import org.gwtbootstrap3.client.ui.Carousel;
import org.gwtbootstrap3.client.ui.Image;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.ImageType;
import org.gwtbootstrap3.client.ui.constants.Responsiveness;

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

    /*
<b:Carousel b:id="carousel-basic">
  <b:CarouselIndicators>
    <b:CarouselIndicator dataTarget="#carousel-basic" dataSlideTo="0" active="true"/>
    <b:CarouselIndicator dataTarget="#carousel-basic" dataSlideTo="1"/>
    <b:CarouselIndicator dataTarget="#carousel-basic" dataSlideTo="2"/>
  </b:CarouselIndicators>
  <b:CarouselInner>
    <b:CarouselSlide active="true">
      <b:Image url=".../>
    </b:CarouselSlide>
  </b:CarouselInner>
  <b:CarouselControl dataTarget="#carousel-basic" prev="true" iconType="..."/>
  <b:CarouselControl dataTarget="#carousel-basic" next="true" iconType="..."/>
</b:Carousel>
    */

    public static class View extends ViewImpl {
        @Inject
        public View() {
            Carousel csel = new Carousel();
//            NOTE: Still need 'slide' class.
//            csel.removeStyleName("slide");
            initWidget(new CarouselBuilder(csel)
                    .interval(2500)
                    // Want 'fade' instead.  This uses override-style.css
                    .addStyleName("carousel-fade")
                    .add(new CarouselIndicatorsBuilder()
                            .add(new CarouselIndicatorBuilder()
                                    .dataTargetWidget(csel)
                                    .dataSlideTo("0")
                                    .active(true)
                                    .carouselIndicator)
                            .add(new CarouselIndicatorBuilder()
                                    .dataTargetWidget(csel)
                                    .dataSlideTo("1")
                                    .carouselIndicator)
                            .carouselIndicators)
                    .add(new CarouselInnerBuilder()
                            .add(new CarouselSlideBuilder()
                                    .active(true)
                                    .add(image(ImageType.ROUNDED))
                                    .carouselSlide)
                            .add(new CarouselSlideBuilder()
                                    .add(image(ImageType.CIRCLE))
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
