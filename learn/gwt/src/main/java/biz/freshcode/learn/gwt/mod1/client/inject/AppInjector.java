package biz.freshcode.learn.gwt.mod1.client.inject;

import biz.freshcode.learn.gwt.mod1.client.EntryPoint;
import biz.freshcode.learn.gwt.mod1.client.experiment.mvp.gwtp.GmdModule;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.gwtplatform.dispatch.rpc.client.gin.RpcDispatchAsyncModule;

@GinModules({AppModule.class, RpcDispatchAsyncModule.class, GmdModule.class})
public interface AppInjector extends Ginjector {
    EntryPoint.Bootstrap bootstrap();
}
