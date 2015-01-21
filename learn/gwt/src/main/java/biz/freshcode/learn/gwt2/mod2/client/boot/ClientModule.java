package biz.freshcode.learn.gwt2.mod2.client.boot;

import biz.freshcode.learn.gwt2.mod2.client.home.Home;
import com.google.gwt.inject.client.AbstractGinModule;
import com.gwtplatform.dispatch.rpc.client.gin.RpcDispatchAsyncModule;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class ClientModule extends AbstractGinModule {
    @Override
    protected void configure() {
        install(new DefaultModule.Builder().build());
        install(new RpcDispatchAsyncModule());
        install(new PresenterModule());
        bindConstant().annotatedWith(DefaultPlace.class).to(Home.TOKEN);
        bindConstant().annotatedWith(ErrorPlace.class).to(Home.TOKEN);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(Home.TOKEN);
    }
}
