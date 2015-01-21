package biz.freshcode.learn.gwt.mod1.client.experiment.mvp.gwtmvp;

import biz.freshcode.learn.gwt.mod1.client.AppPlaceHistoryMapper;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class GwtMvp extends AbstractActivity implements GmView.Presenter {
    @Inject
    GmView view; // this is singleton so fast AND display config remains after navigation.

    @Inject
    PlaceController placeController;

    @Inject
    AppPlaceHistoryMapper placeMapper;

    int num = 0;

    // Assisted means supplied by client code
    @Inject
    public GwtMvp(@Assisted GmPlace gmPlace) {
        // NOTE: Cannot do full construction here because injected fields are not yet present.
        // Although one could get all args supplied in constructor if necessary.
        num = gmPlace.getNum();
    }

    @Override
    public void notifyButtonPressed() {
        // This will cause history to be produced and back button to be enabled.
        placeController.goTo(new GmPlace(num + 1));
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view.setPresenter(this);
        // This must come after the view has been given a presenter
        panel.setWidget(view);
        refresh();
    }

//    Prefer the constructor alternative.
//    public GwtMvp startAt(GmPlace place) {
//        num = place.getNum();
//        return this;
//    }

    private void refresh() {
        int num = this.num;
        String s = "";
        for (int i = 0; i < num; i++) s += "=";
        s += ">";
        view.setHtml(s);
        view.setButtonText("Advance to " + (num + 1));
        view.setLinkTargetToken(linkFor(num - 1));
    }

    /**
     * Convey constructor args to an otherwise injected class.
     */
    public static interface Factory {
        GwtMvp create(GmPlace place);
    }

    private String linkFor(int num) {
        if (num == 0) return ""; // can't get smaller
        return placeMapper.getToken(new GmPlace(num));
    }
}
