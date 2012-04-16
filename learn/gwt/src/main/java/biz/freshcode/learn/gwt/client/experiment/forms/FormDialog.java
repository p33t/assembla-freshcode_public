package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.uispike.builder.DialogBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.TextButtonBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;

import java.util.logging.Logger;

public class FormDialog extends AbstractIsWidget<Dialog> {
    Logger logger = Logger.getLogger(getClass().getName());

    interface Driver extends SimpleBeanEditorDriver<FormBean, FormBeanEditor> {
    }

    private Driver driver = GWT.create(Driver.class);

    @Override
    protected Dialog createWidget() {
        TextButton btnOk;
        FormBeanEditor editor;
        final Dialog dlg = new DialogBuilder()
                .headingText("Forms Demo")
                .width(500)
                .height(300)
                .add(editor = new FormBeanEditor())
                // The predefined buttons are a litle useless.  You have to dig them out again to define handlers (?)
                .predefinedButtons(new Dialog.PredefinedButton[0])
                .addButton(btnOk = new TextButtonBuilder()
                        .text("OK")
                        .textButton)
                .dialog;

        driver.initialize(editor);
        FormBean.Factory factory = GWT.create(FormBean.Factory.class);
        final FormBean b = factory.create().as();
        driver.edit(b);

        btnOk.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                // TODO: Get this working... it is not getting the resulting value.
                String msg = "Finished editting " + b.getStr();
                logger.info(msg);
                Info.display("Note", msg);
                dlg.hide((TextButton) event.getSource());
            }
        });
        return dlg;
    }
}
