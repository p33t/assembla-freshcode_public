package biz.freshcode.learn.gwt.client.experiment.mvp.gwtp;

import com.google.gwt.user.client.ui.HTML;

public class GmdView extends AbstractView<HTML> implements GwtpMvpDemo.View {
    @Override
    public void appendHtml(String html) {
        asWidget().setHTML(html);
    }

    @Override
    protected HTML createWidget() {
        return new HTML("<p>Created</p>");
    }
}
