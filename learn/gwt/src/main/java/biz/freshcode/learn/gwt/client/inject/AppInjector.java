package biz.freshcode.learn.gwt.client.inject;

import biz.freshcode.learn.gwt.client.EntryPoint;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;

@GinModules({AppModule.class, DispatchAsyncModule.class})
public interface AppInjector extends Ginjector {
    EntryPoint.Bootstrap bootstrap();
}
