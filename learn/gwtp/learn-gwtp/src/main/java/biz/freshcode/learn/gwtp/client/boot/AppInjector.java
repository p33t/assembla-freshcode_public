package biz.freshcode.learn.gwtp.client.boot;

import biz.freshcode.learn.gwtp.client.home.Home;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

@GinModules({AppModule.class})
public interface AppInjector extends Ginjector {
    EventBus getEventBus();

    DispatchAsync getDispatcher();

    PlaceManager getPlaceManager();

    Provider<Home> getHome();
}
