package biz.freshcode.learn.gwtp.client.compound;

import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class CompoundViewImpl extends ViewImpl implements Compound.View {
    @Inject
    public CompoundViewImpl() {
        initWidget(new HTML("<p>Compound</p>"));
    }
}
