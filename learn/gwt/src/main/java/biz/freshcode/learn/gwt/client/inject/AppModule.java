package biz.freshcode.learn.gwt.client.inject;

import biz.freshcode.learn.gwt.client.AppActivityMapper;
import biz.freshcode.learn.gwt.client.AppPlaceHistoryMapper;
import biz.freshcode.learn.gwt.client.experiment.mvp.gwtmvp.GmView;
import biz.freshcode.learn.gwt.client.experiment.mvp.gwtmvp.GmViewImpl;
import biz.freshcode.learn.gwt.client.experiment.mvp.gwtmvp.GwtMvp;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * Bindings for DI.
 * Notes:
 * - Don't need bindings for remote 'async' services.  They are automatically bound to their non-async counterparts.
 */
public class AppModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(GmView.class).to(GmViewImpl.class);
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(ActivityMapper.class).to(AppActivityMapper.class);
        bind(PlaceHistoryMapper.class).to(AppPlaceHistoryMapper.class).in(Singleton.class);
//        bind(GwtMvp.class).toProvider(FactoryProvider.newFactory(GwtMvpFactory.class, GwtMvp.class));
//        install(new FactoryModuleBuilder().build(GwtMvpFactory.class));
        install(new GinFactoryModuleBuilder()
// Doesn't help...                .implement(GwtMvp.class, GwtMvp.class)
                .build(GwtMvp.Factory.class)
        );
    }

    ///////////////////////////////////////////////////////////////
    // These specialized provider methods are needed
    // for when we don't have control over source code
    ///////////////////////////////////////////////////////////////


    @Provides
    @Singleton
    public PlaceHistoryHandler getHistoryHandler(PlaceHistoryMapper historyMapper) {
        return new PlaceHistoryHandler(historyMapper);
    }

    @Provides
    @Singleton
    public PlaceController getPlaceController(EventBus eventBus) {
        return new PlaceController(eventBus);
    }

    @Provides
    @Singleton
    public ActivityManager getActivityManager(ActivityMapper mapper,
                                              EventBus eventBus) {
        return new ActivityManager(mapper, eventBus);
    }
}
