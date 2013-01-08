package biz.freshcode.learn.gwt.client.experiment.dynamiclayout;

import biz.freshcode.learn.gwt.client.builder.gxt.WindowBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.VBoxLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.form.TextAreaBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.container.BoxLayoutDataBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;

import static com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode.AUTOY;
import static com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign.STRETCH;

class DlWindow extends AbstractIsWidget<Window> {

    @Override
    protected Window createWidget() {
        return new WindowBuilder()
                .headingText("Dynamic Layout Demo")
                .widget(new VBoxLayoutContainerBuilder()
                        .vBoxLayoutAlign(STRETCH)
                        .add(new FlowLayoutContainerBuilder()
                                .scrollMode(AUTOY)
                                .add(new HtmlLayoutContainer("[None]"))
                                .flowLayoutContainer)
                        .add(new TextAreaBuilder()
                                .text("Add / Remove messages and note how window behaves\nBonus line")
                                .textArea,
                                new BoxLayoutDataBuilder()
                                        .flex(1)
                                        .boxLayoutData)
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
                .window;
    }

    private void clearMsgs() {
        Info.display("Clear Msg", "x");
    }

    private void addMsg() {
        Info.display("Add Msg", "x");
    }
}
