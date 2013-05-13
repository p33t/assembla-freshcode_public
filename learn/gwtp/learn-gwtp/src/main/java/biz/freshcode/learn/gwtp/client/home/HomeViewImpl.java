package biz.freshcode.learn.gwtp.client.home;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.widget.client.TextButton;
import com.gwtplatform.mvp.client.ViewImpl;

public class HomeViewImpl extends ViewImpl implements Home.View {
    private TextButton btnRpc;
    private HTML fb;

    public HomeViewImpl() {
        GWT.log("Creating widget");
        VerticalPanel pnl = new VerticalPanel();
        pnl.add(new HTML("<p>Home</p>"));
        pnl.add(btnRpc = new TextButton("RPC"));
        pnl.add(fb = new HTML("&nbsp;"));
        initWidget(pnl);
    }

    @Override
    public TextButton getBtnRpc() {
        return btnRpc;
    }

    @Override
    public void appendMsg(String msg) {
        fb.setHTML(fb.getHTML() + "<p>" + msg + "</p>");
    }
}
