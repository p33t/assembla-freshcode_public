package biz.freshcode.learn.gwtp.client.boot;

import biz.freshcode.learn.gwtp.client.compound.*;
import biz.freshcode.learn.gwtp.client.home.Home;
import biz.freshcode.learn.gwtp.client.home.HomeViewImpl;
import biz.freshcode.learn.gwtp.client.parent.Parent;
import biz.freshcode.learn.gwtp.client.parent.ParentViewImpl;
import biz.freshcode.learn.gwtp.shared.AppUtil;
import biz.freshcode.learn.gwtp.shared.boot.SessionInfo;
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
    private static long localBootTime;

    public static void init(SessionInfo info, long localBootTime) {
        AppModule.localBootTime = localBootTime;
        SessionInfoProvider.init(info);
    }

    @Override
    protected void configure() {
        bindConstant().annotatedWith(SecurityCookie.class).to(AppUtil.XSRF_COOKIE);
        bindConstant().annotatedWith(DefaultPlace.class).to(Home.TOKEN);
        bindConstant().annotatedWith(ErrorPlace.class).to(Home.TOKEN);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(Home.TOKEN);

        // NOTE: Local boot time and SessionInfo.bootTime indicate client clock offset.
        bindConstant().annotatedWith(LocalBootTime.class).to(localBootTime);
        bind(SessionInfo.class).toProvider(SessionInfoProvider.class);

        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        install(new DefaultModule(DefaultPlaceManager.class));
        install(new DispatchAsyncModule());

        bindPresenter(Root.class, Root.Proxy.class);
        bindPresenter(Parent.class, Parent.View.class, ParentViewImpl.class, Parent.Proxy.class);
        bindPresenter(Home.class, Home.View.class, HomeViewImpl.class, Home.Proxy.class);
        bindPresenter(Compound.class, Compound.View.class, CompoundViewImpl.class, Compound.Proxy.class);
        bindPresenter(Child1.class, Child1.View.class, Child1ViewImpl.class, Child1.Proxy.class);
        bindPresenter(Child2.class, Child2.View.class, Child2ViewImpl.class, Child2.Proxy.class);
    }
}
