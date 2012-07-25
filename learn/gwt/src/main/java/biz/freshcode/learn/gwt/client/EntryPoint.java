package biz.freshcode.learn.gwt.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class EntryPoint implements com.google.gwt.core.client.EntryPoint {
    public static final EventBus EVENT_BUS = new SimpleEventBus(); // TODO: Dodgy?
    MainPanel appWidget = new MainPanel();

    @Override
    public void onModuleLoad() {
        PlaceController placeController = new PlaceController(EVENT_BUS);

        ActivityMapper activityMapper = new AppActivityMapper();
        ActivityManager activityManager = new ActivityManager(activityMapper, EVENT_BUS);
        activityManager.setDisplay(appWidget);

        // Start PlaceHistoryHandler with our PlaceHistoryMapper
        AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, EVENT_BUS, AppActivityMapper.DEFAULT_PLACE);


        // Remove 'loading' spinner.
        DOM.removeChild(RootPanel.getBodyElement(), DOM.getElementById("loading"));

        RootLayoutPanel.get().add(appWidget);
        // Goes to the place represented on URL else default place
        historyHandler.handleCurrentHistory();
    }
}
