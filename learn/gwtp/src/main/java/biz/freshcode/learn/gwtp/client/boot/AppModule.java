package biz.freshcode.learn.gwtp.client.boot;

import biz.freshcode.learn.gwtp.client.compound.*;
import biz.freshcode.learn.gwtp.client.editform.EditForm;
import biz.freshcode.learn.gwtp.client.editform.EditFormViewImpl;
import biz.freshcode.learn.gwtp.client.home.Home;
import biz.freshcode.learn.gwtp.client.home.HomeViewImpl;
import biz.freshcode.learn.gwtp.client.paginggrid.PagingGrid;
import biz.freshcode.learn.gwtp.client.paginggrid.PagingGridViewImpl;
import biz.freshcode.learn.gwtp.client.popup.PopupDemo;
import biz.freshcode.learn.gwtp.client.slotless.SlotlessChild;
import biz.freshcode.learn.gwtp.client.slotless.SlotlessDemo;
import biz.freshcode.learn.gwtp.client.ext.ExtModule;
import biz.freshcode.learn.gwtp.client.special.SpecialModule;
import biz.freshcode.learn.gwtp.shared.AppUtil;
import biz.freshcode.learn.gwtp.shared.boot.SessionInfo;
import com.gwtplatform.dispatch.rpc.client.gin.RpcDispatchAsyncModule;
import com.gwtplatform.dispatch.shared.SecurityCookie;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.client.proxy.DefaultPlaceManager;

public class AppModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bindConstant().annotatedWith(SecurityCookie.class).to(AppUtil.XSRF_COOKIE);
        bindConstant().annotatedWith(DefaultPlace.class).to(Home.TOKEN);
        bindConstant().annotatedWith(ErrorPlace.class).to(Home.TOKEN);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(Home.TOKEN);

        bind(SessionInfo.class).toProvider(SessionInfoProvider.class);

//Shouldn't need this...bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        install(new DefaultModule(DefaultPlaceManager.class));
        install(new RpcDispatchAsyncModule());

        bindPresenter(Root.class, Root.Proxy.class);
        bindPresenter(Home.class, Home.View.class, HomeViewImpl.class, Home.Proxy.class);
        bindPresenter(Compound.class, Compound.View.class, CompoundViewImpl.class, Compound.Proxy.class);
        bindPresenter(Child1.class, Child1.View.class, Child1ViewImpl.class, Child1.Proxy.class);
        bindPresenter(Child2.class, Child2.View.class, Child2ViewImpl.class, Child2.Proxy.class);
        bindPresenter(PagingGrid.class, PagingGrid.View.class, PagingGridViewImpl.class, PagingGrid.Proxy.class);
        bindPresenter(EditForm.class, EditForm.View.class, EditFormViewImpl.class, EditForm.Proxy.class);
        bindPresenter(PopupDemo.class, PopupDemo.Proxy.class);
        bindPresenter(SlotlessDemo.class, SlotlessDemo.Proxy.class);
        bindPresenter(SlotlessChild.class, SlotlessChild.Proxy.class);

        install(new ExtModule());
        install(new SpecialModule());
    }
}
