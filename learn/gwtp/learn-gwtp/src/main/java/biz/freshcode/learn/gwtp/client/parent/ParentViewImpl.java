package biz.freshcode.learn.gwtp.client.parent;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class ParentViewImpl extends ViewImpl implements Parent.View {
    private final SimplePanel pnl = new SimpleLayoutPanel();

    @Inject
    public ParentViewImpl() {
        VerticalPanel vert = new VerticalPanel();
        vert.setWidth("500px");
        vert.add(new HTML("Heading"));
        vert.add(pnl);
        vert.setHeight("500px");
        pnl.setWidth("500px");
        pnl.setHeight("400px");
        initWidget(vert);
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (slot == Parent.SLOT) {
            GWT.log("Setting SLOT content: " + content);
//            pnl.clear();
            if (content != null) pnl.setWidget(content);
        } else {
            super.setInSlot(slot, content);
        }
    }
}
