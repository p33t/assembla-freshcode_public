package biz.freshcode.learn.gwt.client.experiment.mvp.gwtp;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.SimpleEventBus;

@GinModules({})
public interface GmdInjector extends Ginjector {
    GmdPlaceManager getPlaceManager();
    SimpleEventBus getEventBus();
    Provider<GwtpMvpDemo> getSomethingNeededByGwtp();
}
