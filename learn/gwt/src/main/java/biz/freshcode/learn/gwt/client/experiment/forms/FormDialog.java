package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.uispike.builder.DialogBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.TextButtonBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class FormDialog extends AbstractIsWidget<Dialog> {
    @Override
    protected Dialog createWidget() {
        TextButton btnOk;
        final Dialog dlg = new DialogBuilder()
                .headingText("Forms Demo")
                .width(500)
                .height(300)
                .add(new VerticalLayoutContainerBuilder()
                        // TODO: Work out dialog buttons.
                        .add(btnOk = new TextButtonBuilder()
                                .text("OK")
                                .textButton)
                        .verticalLayoutContainer)
                .dialog;

        btnOk.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                dlg.hide((TextButton) event.getSource());
            }
        });
        return dlg;
    }
}
