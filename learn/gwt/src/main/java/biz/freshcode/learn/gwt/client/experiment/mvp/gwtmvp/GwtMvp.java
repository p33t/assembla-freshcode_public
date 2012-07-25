package biz.freshcode.learn.gwt.client.experiment.mvp.gwtmvp;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class GwtMvp extends AbstractActivity implements GmView.Presenter {
    @Inject
    GmView view; // this is singleton so fast AND display config remains after navigation.

    @Inject
    PlaceController placeController;

    int num = 0;

    @Override
    public void notifyButtonPressed() {
        // This will cause history to be produced and back button to be enabled.
        placeController.goTo(new GmPlace(num + 1));
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        panel.setWidget(view);
        view.setPresenter(this);
        refresh();
    }

    public GwtMvp goTo(GmPlace place) {
        num = place.getNum();
        return this;
    }

    private void refresh() {
        int num = this.num;
        String s = "";
        for (int i = 0; i < num; i++) s += "=";
        s += ">";
        view.setHtml(s);
        view.setButtonText("Advance to " + (num + 1));
    }
}
