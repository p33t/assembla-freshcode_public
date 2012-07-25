package biz.freshcode.learn.gwt.client.experiment.mvp.gwtmvp;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class GwtMvp extends AbstractActivity implements GmView.Presenter {
    GmView view = new GmViewImpl(); // NOTE: This is manually instantiated but typically supplied with DI.
    int num = 0;

    public GwtMvp(GmPlace place) {
        this(place.getNum());
    }

    public GwtMvp() {
        this(0);
    }

    public GwtMvp(int num) {
        this.num = num;
    }

    @Override
    public void notifyButtonPressed() {
        num++;
        refresh();
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        panel.setWidget(view);
        view.setPresenter(this);
        refresh();
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
