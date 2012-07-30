package biz.freshcode.learn.gwt.client.experiment.toolbar;

import biz.freshcode.learn.gwt.client.IsRootContent;
import biz.freshcode.learn.gwt.client.uispike.builder.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.ToolBarBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class ToolBarDemo extends AbstractIsWidget implements IsRootContent {
    private LabelToolItem lbl;
    private ToolBar toolBar;

    @Override
    protected Widget createWidget() {
        return new BorderLayoutContainerBuilder()
                .northWidget(toolBar = new ToolBarBuilder()
                        .add(new FillToolItem())
                        .add(new ToolButton(ToolButton.LEFT, new SelectEvent.SelectHandler() {
                            @Override
                            public void onSelect(SelectEvent event) {
                                changeLabel("Small Label");
                            }
                        }))
                        .add(new SeparatorToolItem())
                        .add(lbl = new LabelToolItem("Starting label... that is very long"))
                        .add(new SeparatorToolItem())
                        .add(new ToolButton(ToolButton.RIGHT, new SelectEvent.SelectHandler() {
                            @Override
                            public void onSelect(SelectEvent event) {
                                changeLabel("A very big label that will need resize");
                            }
                        }))
                        .add(new FillToolItem())
                        .toolBar)
                .borderLayoutContainer;
    }

    private void changeLabel(String str) {
        lbl.setLabel(str);
        toolBar.forceLayout();
    }
}
