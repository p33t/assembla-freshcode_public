package biz.freshcode.learn.gwtp.client.home;

import biz.freshcode.learn.gwtp.client.util.AbstractView;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class HomeViewImpl extends AbstractView implements Home.View {
    @Override
    protected Widget createWidget() {
        GWT.log("Creating widget");
        VerticalPanel pnl = new VerticalPanel();
        pnl.add(new HTML("<p>Home</p>"));
        return pnl;
    }
}
