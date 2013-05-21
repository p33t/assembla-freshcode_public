package biz.freshcode.learn.gwtp.client.paginggrid;

import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class PagingGridViewImpl extends ViewImpl implements PagingGrid.View {

    @Inject
    public PagingGridViewImpl() {
        initWidget(new HTML("<p>Grid</p>"));
    }
}
