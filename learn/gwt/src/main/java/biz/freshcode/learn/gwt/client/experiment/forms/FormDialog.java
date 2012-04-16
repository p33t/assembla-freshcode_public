package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.uispike.builder.DialogBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.TextButtonBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;

import java.util.logging.Logger;

public class FormDialog extends AbstractIsWidget<Dialog> {
    Logger logger = Logger.getLogger(getClass().getName());
    private AutoBean<FormBean> formBean;

    interface Driver extends SimpleBeanEditorDriver<FormBean, FormBeanEditor> {
    }

    private Driver driver = GWT.create(Driver.class);

    public FormDialog(AutoBean<FormBean> formBean) {
        this.formBean = formBean;
    }

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

        // Initialize editing
        driver.initialize(editor);
        driver.edit(formBean.as());

        btnOk.addSelectHandler(new SelectEvent.SelectHandler() {
            public void onSelect(SelectEvent event) {
                onOk(event, dlg);
            }
        });
        return dlg;
    }

    private void onOk(SelectEvent event, Dialog dlg) {
        if (driver.isDirty()) {
            driver.flush();
            if (driver.hasErrors()) {
                String msg = "";
                for (EditorError e: driver.getErrors()) {
                    msg += e.getMessage();
                }
                Info.display("Error", msg);
            } else {
                String json = AutoBeanCodex.encode(formBean).getPayload();
                String msg = "Finished editting...\n" + json;
                logger.info(msg);
                Info.display("Note", msg);
                dlg.hide((TextButton) event.getSource());
            }
        }
        else Info.display("Note", "No Changes");
    }
}
