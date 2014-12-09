package biz.freshcode.learn.gwtp.client.home;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtplatform.mvp.client.ViewImpl;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class HomeViewImpl extends ViewImpl implements Home.View {
    private TextButton btnAction;
    private HTML fb;

    public HomeViewImpl() {
        GWT.log("Creating widget");
        VerticalPanel pnl = new VerticalPanel();
        pnl.add(new HTML("<p>Home</p>"));
        pnl.add(btnAction = new TextButton("Action"));
        pnl.add(fb = new HTML("&nbsp;"));
        initWidget(pnl);
    }

    @Override
    public TextButton getBtnAction() {
        return btnAction;
    }

    @Override
    public void appendMsg(String msg) {
        fb.setHTML(fb.getHTML() + "<p>" + msg + "</p>");
    }
}
