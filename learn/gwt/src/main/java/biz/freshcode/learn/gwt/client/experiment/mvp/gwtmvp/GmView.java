package biz.freshcode.learn.gwt.client.experiment.mvp.gwtmvp;

import com.google.gwt.user.client.ui.IsWidget;

interface GmView extends IsWidget {
    void setButtonText(String s);
    void setHtml(String s);
    void setPresenter(Presenter p);

    interface Presenter {
        void notifyButtonPressed();
    }
}
