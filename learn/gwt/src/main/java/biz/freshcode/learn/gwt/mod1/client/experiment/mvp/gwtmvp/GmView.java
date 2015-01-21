package biz.freshcode.learn.gwt.mod1.client.experiment.mvp.gwtmvp;

import com.google.gwt.user.client.ui.IsWidget;

public interface GmView extends IsWidget {
    void setButtonText(String s);
    void setHtml(String s);
    void setPresenter(Presenter p);

    void setLinkTargetToken(String s);

    interface Presenter {
        void notifyButtonPressed();
    }
}
