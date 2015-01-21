package biz.freshcode.learn.gwt.mod1.client.experiment.toolbar;

import biz.freshcode.learn.gwt.mod1.client.IsRootContent;
import biz.freshcode.learn.gwt.mod1.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.PopupBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.button.ToolButtonBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.tips.ToolTipConfigBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.toolbar.ToolBarBuilder;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.DatePicker;
import com.sencha.gxt.widget.core.client.Popup;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import java.util.Date;

public class ToolBarDemo extends AbstractIsWidget implements IsRootContent {
    private LabelToolItem lbl;
    private ToolBar toolBar;
    private ToolButton dater;

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
                        .add(new SeparatorToolItem())
                        .add(dater = new ToolButton(ToolButton.MAXIMIZE, new DaterHandler()))
                        .add(new SeparatorToolItem())
                        .add(new ToolButtonBuilder(ToolButton.GEAR)
                                .toolTipConfig(new ToolTipConfigBuilder()
                                        .bodyHtml("<p>Welcome to the tool tip<br/>New Line!</p>")
                                        .dismissDelay(0) // not sure what this does.
                                        .toolTipConfig)
                                .toolButton)
                        .add(new FillToolItem())
                        .toolBar)
                .borderLayoutContainer;
    }

    private void changeLabel(String str) {
        lbl.setLabel(str);
        toolBar.forceLayout();
    }

    private class DaterHandler implements SelectEvent.SelectHandler {
        @Override
        public void onSelect(SelectEvent event) {
            DatePicker picker;
            final Popup popup = new PopupBuilder()
                    .add(picker = new DatePicker())
                    .borders(true)
                    .popup;
            picker.addValueChangeHandler(new ValueChangeHandler<Date>() {
                @Override
                public void onValueChange(ValueChangeEvent<Date> evt) {
                    popup.hide();
                    Info.display("Chose", evt.getValue().toString());
                }
            });
            popup.show(dater);
        }
    }
}
