package biz.freshcode.learn.gwt.client.experiment.mvp.gwtp;

import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class GmdModule extends AbstractPresenterModule {
    public static final String GMD = "gmd";

    @Override
    protected void configure() {
// Double binds...        bind(PlaceManager.class).to(GmdPlaceManager.class);
        install(new DefaultModule(GmdPlaceManager.class));
        bindConstant().annotatedWith(DefaultPlace.class).to(GMD);
        bindConstant().annotatedWith(ErrorPlace.class).to(GMD);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(GMD);
        bindPresenter(GwtpMvpDemo.class, GwtpMvpDemo.View.class, GmdView.class, GwtpMvpDemo.Proxy.class);
    }
}
