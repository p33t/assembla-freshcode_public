package biz.freshcode.learn.gwt.mod1.client.experiment.dynamiclayout;

import biz.freshcode.learn.gwt.mod1.client.builder.gxt.WindowBuilder;
import biz.freshcode.learn.gwt.mod1.client.builder.gxt.container.BoxLayoutDataBuilder;
import biz.freshcode.learn.gwt.mod1.client.builder.gxt.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt.mod1.client.builder.gxt.container.VBoxLayoutContainerBuilder;
import biz.freshcode.learn.gwt.mod1.client.builder.gxt.form.TextAreaBuilder;
import biz.freshcode.learn.gwt.mod1.client.util.AbstractIsWidget;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import static com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode.AUTOY;
import static com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign.STRETCH;

class DlWindow extends AbstractIsWidget<Window> {
    private FlowLayoutContainer flc;

    @Override
    protected Window createWidget() {
        return new WindowBuilder()
                .headingText("Dynamic Layout Demo")
                .widget(new VBoxLayoutContainerBuilder()
                        .vBoxLayoutAlign(STRETCH)
                        .add(new TextAreaBuilder()
                                .text("Add / Remove messages and note how window behaves\nBonus line")
                                .textArea,
                                new BoxLayoutDataBuilder()
                                        .flex(1)
                                        .boxLayoutData)
                                // 'Tis better underneath because less jarring for user
                        .add(flc = new FlowLayoutContainerBuilder()
                                .scrollMode(AUTOY)
                                .flowLayoutContainer)
                        .vBoxLayoutContainer)
                .width(300)
                .height(200)
                .addButton(new TextButton("Add Msg", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        addMsg();
                    }
                }))
                .addButton(new TextButton("Clear Msgs", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        clearMsgs();
                    }
                }))
                .addButton(new TextButton("Toggle State", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        toggleState();
                    }
                }))
                .window;
    }

    private void toggleState() {
        flc.setEnabled(!flc.isEnabled());
    }

    private void clearMsgs() {
        flc.clear();
        forceLayout();
    }

    private void addMsg() {
        flc.add(new HtmlLayoutContainer("Message " + (flc.getWidgetCount() + 1)));
        forceLayout();
    }

    private void forceLayout() {
        asWidget().forceLayout(); // otherwise message is behind (why isn't scroll kicking in?)
    }
}
