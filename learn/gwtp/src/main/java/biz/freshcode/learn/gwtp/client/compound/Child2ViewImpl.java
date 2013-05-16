package biz.freshcode.learn.gwtp.client.compound;

import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class Child2ViewImpl extends ViewImpl implements Child2.View {
    @Inject
    public Child2ViewImpl() {
        initWidget(new HTML("<p>Child 2 Content</p>"));
    }
}
