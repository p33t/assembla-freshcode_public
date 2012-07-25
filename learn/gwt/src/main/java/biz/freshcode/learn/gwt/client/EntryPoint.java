package biz.freshcode.learn.gwt.client;

import biz.freshcode.learn.gwt.client.inject.AppInjector;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;

import static biz.freshcode.learn.gwt.client.AppActivityMapper.DEFAULT_PLACE;

public class EntryPoint implements com.google.gwt.core.client.EntryPoint {
    // TODO: Should this be a constant?
    public static final AppInjector INJECTOR = GWT.create(AppInjector.class);

    @Override
    public void onModuleLoad() {
        MainPanel mainPanel = new MainPanel();

        // TODO: Perhaps have an internal injectable entrypoint implementation that doesn't use INJECTOR so much.
        INJECTOR.activityManager().setDisplay(mainPanel);

        // Start PlaceHistoryHandler with our PlaceHistoryMapper
        INJECTOR.historyHandler().register(INJECTOR.placeController(), INJECTOR.eventBus(), DEFAULT_PLACE);


        // Remove 'loading' spinner.
        DOM.removeChild(RootPanel.getBodyElement(), DOM.getElementById("loading"));

        RootLayoutPanel.get().add(mainPanel);
        // Goes to the place represented on URL else default place
        INJECTOR.historyHandler().handleCurrentHistory();
    }
}
