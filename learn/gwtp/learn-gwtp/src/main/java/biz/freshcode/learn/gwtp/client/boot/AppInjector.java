package biz.freshcode.learn.gwtp.client.boot;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.gwtplatform.dispatch.shared.DispatchAsync;

@GinModules({AppModule.class})
public interface AppInjector extends Ginjector {
    DispatchAsync getDispatch();
}
