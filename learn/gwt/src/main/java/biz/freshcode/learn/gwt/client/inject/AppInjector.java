package biz.freshcode.learn.gwt.client.inject;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.web.bindery.event.shared.EventBus;

@GinModules(AppModule.class)
public interface AppInjector extends Ginjector {
    ActivityManager activityManager();

    PlaceController placeController();

    PlaceHistoryHandler historyHandler();

    EventBus eventBus();
}
