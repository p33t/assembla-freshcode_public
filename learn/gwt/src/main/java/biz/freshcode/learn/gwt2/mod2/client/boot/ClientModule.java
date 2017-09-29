package biz.freshcode.learn.gwt2.mod2.client.boot;

import biz.freshcode.learn.gwt2.mod2.client.home.Home;
import com.google.gwt.inject.client.AbstractGinModule;
import com.gwtplatform.dispatch.rest.client.RestApplicationPath;
import com.gwtplatform.dispatch.rest.client.gin.RestDispatchAsyncModule;
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

        RestDispatchAsyncModule.Builder rest = new RestDispatchAsyncModule.Builder();
        rest.requestTimeout(30000); // 30s timeout
//        rest.exceptionHandler()
        install(rest.build());

        bindConstant().annotatedWith(RestApplicationPath.class).to("/api/v1");

        install(new PresenterModule());
        bindConstant().annotatedWith(DefaultPlace.class).to(Home.TOKEN);
        bindConstant().annotatedWith(ErrorPlace.class).to(Home.TOKEN);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(Home.TOKEN);
    }
}
