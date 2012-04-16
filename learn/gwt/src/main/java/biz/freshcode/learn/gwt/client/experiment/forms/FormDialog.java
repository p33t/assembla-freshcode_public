package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.uispike.builder.DialogBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.TextButtonBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import java.util.logging.Logger;

public class FormDialog extends AbstractIsWidget<Dialog> {

    Logger logger = Logger.getLogger(getClass().getName());

    @Override
    protected Dialog createWidget() {
        TextButton btnOk;
        final Dialog dlg = new DialogBuilder()
                .headingText("Forms Demo")
                .width(500)
                .height(300)
                .add(new VerticalLayoutContainerBuilder()
                        .add(new HTMLPanel("<p>This is the main area</p>"))
                        .verticalLayoutContainer)
                // The predefined buttons are a litle useless.  You have to dig them out again to define handlers (?)
                .predefinedButtons(new Dialog.PredefinedButton[0])
                .addButton(btnOk = new TextButtonBuilder()
                        .text("OK")
                        .textButton)
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
