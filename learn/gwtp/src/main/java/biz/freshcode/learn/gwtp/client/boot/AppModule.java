package biz.freshcode.learn.gwtp.client.boot;

import biz.freshcode.learn.gwtp.client.home.Home;
import biz.freshcode.learn.gwtp.client.home.HomeViewImpl;
import biz.freshcode.learn.gwtp.client.parent.Parent;
import biz.freshcode.learn.gwtp.client.parent.ParentViewImpl;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.inject.Singleton;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.dispatch.shared.SecurityCookie;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.client.proxy.DefaultPlaceManager;

public class AppModule extends AbstractPresenterModule {
    public static final String XSRF_COOKIE = "XSRF-SAFETY";

    @Override
    protected void configure() {
        bindConstant().annotatedWith(SecurityCookie.class).to(AppModule.XSRF_COOKIE);
        bindConstant().annotatedWith(DefaultPlace.class).to(Home.TOKEN);
        bindConstant().annotatedWith(ErrorPlace.class).to(Home.TOKEN);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(Home.TOKEN);

        bind(SessionInfo.class).toProvider(SessionInfoProvider.class);
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        install(new DefaultModule(DefaultPlaceManager.class));
        install(new DispatchAsyncModule());

        bindPresenter(Home.class, Home.View.class, HomeViewImpl.class, Home.Proxy.class);
        bindPresenter(Parent.class, Parent.View.class, ParentViewImpl.class, Parent.Proxy.class);
    }
}
