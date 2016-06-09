package biz.freshcode.learn.gwt.client.experiment.layout;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.DialogBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.BorderLayoutDataBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.HTML;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import static com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;

public class LayoutDemo extends AbstractIsWidget<FlowLayoutContainer> {
    @Override
    protected FlowLayoutContainer createWidget() {
        return new FlowLayoutContainerBuilder()
                .add(new TextButton("Dialog", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        dialog();
                    }
                }))
                .flowLayoutContainer;
    }

    private void dialog() {
        final VerticalLayoutContainer north = new VerticalLayoutContainerBuilder()
                .scrollMode(ScrollMode.AUTOY)
                .add(new HTML("<p>North</p><p>North</p><p>North</p><p>North</p><p>North</p><p>North</p><p>North</p>"))
                .verticalLayoutContainer;
        final BorderLayoutContainer blc = new BorderLayoutContainer();
        final BorderLayoutContainer.BorderLayoutData layoutData = new BorderLayoutDataBuilder()
                .split(true)
                .collapsible(true)
                .minSize(20)
//                                        .size(40)
                .borderLayoutData;

        new DialogBuilder()
                .modal(true)
                .resizable(true)
                .pixelSize(640, 480)
                .heading("Resize this dialog")
                .hideOnButtonClick(true)

/* No resizing / size control
                .widget(new NorthSouthContainerBuilder()
                        .resize(true)
                        .northWidget(new FlowLayoutContainerBuilder()
                                .scrollMode(ScrollMode.ALWAYS)
                                .height(30)
                                .add(new HTML("<p>North</p><p>North</p><p>North</p><p>North</p><p>North</p><p>North</p><p>North</p>"))
                                .flowLayoutContainer)
//                        .northWidget(new CssFloatLayoutContainerBuilder()
//                                .scrollMode(ScrollMode.ALWAYS)
//                                .height(30)
//                                .add(new HTML("<p>North</p><p>North</p><p>North</p><p>North</p><p>North</p><p>North</p><p>North</p>"))
//                                .cssFloatLayoutContainer)
                        .southWidget(new HTML("<p>South</p>"))
                        .northSouthContainer)
*/

                .widget(new BorderLayoutContainerBuilder(blc)
// Scroll mode doesn't work properly  .northWidget(new CssFloatLayoutContainerBuilder()
                        .centerWidget(new FlowLayoutContainerBuilder()
                                .add(new TextButton("Show/Hide", new SelectEvent.SelectHandler() {
                                    @Override
                                    public void onSelect(SelectEvent event) {
                                        if (north.isVisible()) north.removeFromParent();
                                        else blc.setNorthWidget(north, layoutData);
                                        blc.forceLayout();
                                    }
                                }))
                                .flowLayoutContainer)
                        .borderLayoutContainer)
                .dialog.show();
    }
}
