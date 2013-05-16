package biz.freshcode.learn.gwtp.client.compound;

import biz.freshcode.learn.gwtp.client.builder.gxt.ContentPanelBuilder;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.sencha.gxt.widget.core.client.ContentPanel;

public class CompoundViewImpl extends ViewImpl implements Compound.View {
    private final ContentPanel pnl;

    @Inject
    public CompoundViewImpl() {
        initWidget(pnl = new ContentPanelBuilder()
// Messes up heading...                .headerVisible(false)
                .headingText("(Empty)")
                .bodyBorder(true)
                .contentPanel);
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (slot == Compound.SLOT) {
            pnl.clear();
            if (content != null) pnl.setWidget(content);
            syncHeading(content);
        } else {
            super.setInSlot(slot, content);
        }
    }

    private void syncHeading(IsWidget content) {
        if (content instanceof Titled) {
            pnl.setHeaderVisible(true);
            pnl.setHeadingText(((Titled) content).getTitle());
        } else {
            pnl.setHeaderVisible(false);
        }
        pnl.forceLayout();
    }
}
