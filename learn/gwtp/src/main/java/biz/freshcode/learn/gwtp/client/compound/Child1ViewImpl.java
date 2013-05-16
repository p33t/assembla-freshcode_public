package biz.freshcode.learn.gwtp.client.compound;

import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class Child1ViewImpl extends ViewImpl implements Child1.View {
    @Inject
    public Child1ViewImpl() {
        initWidget(new HTML("<p>Child 1 Content</p>"));
    }
}
