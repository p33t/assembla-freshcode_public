package biz.freshcode.learn.gwtp.client.boot;

import biz.freshcode.learn.gwtp.client.util.IsWidgetImpl;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;

@Singleton
public class PageTitle extends IsWidgetImpl<LabelToolItem> {

    @Inject
    public PageTitle() {
        initWidget(new LabelToolItem("Starting..."));
    }

    public void setTitle(String s) {
        asWidget().setLabel(s);
    }
}
