package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.uispike.builder.DialogBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.TextButtonBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;

import java.util.logging.Logger;

public class FormDialog extends AbstractIsWidget<Dialog> {
    Logger logger = Logger.getLogger(getClass().getName());
    private AutoBean<FormBean> formBean;
    private AutoBean<FormBean> original;

    interface Driver extends SimpleBeanEditorDriver<FormBean, FormBeanEditor> {
    }

    private Driver driver = GWT.create(Driver.class);

    public FormDialog(AutoBean<FormBean> formBean) {
        this.formBean = formBean;
        logger.info("Init with ...\n" + getFormBeanJson());
    }

    @Override
    protected Dialog createWidget() {
        logger.info("Creating widget");
        TextButton btnClose;
        FormBeanEditor editor;
        final Dialog dlg = new DialogBuilder()
                .headingText("Forms Demo")
                .width(500)
                .height(300)
                .add(editor = new FormBeanEditor())
                // The predefined buttons are a litle useless.  You have to dig them out again to define handlers (?)
                .predefinedButtons(new Dialog.PredefinedButton[0])
                // TODO: Replace with predefined button that closes the dialog
                .addButton(btnClose = new TextButtonBuilder()
                        .text("Close")
                        .textButton)
                .dialog;

        // Initialize editing
        driver.initialize(editor);
        // Trying to debug
        original = AutoBeanCodex.decode(GWT.<AutoBeanFactory>create(FormBean.Factory.class), FormBean.class, AutoBeanCodex.encode(formBean));
        driver.edit(formBean.as());

        btnClose.addSelectHandler(new SelectEvent.SelectHandler() {
            public void onSelect(SelectEvent event) {
                onClose(event, dlg);
            }
        });
        return dlg;
    }

    // TODO: Tap into 'close' life cycle to properly
    private void onClose(SelectEvent event, Dialog dlg) {
        // trying to debug
        driver.flush();
        String json = getFormBeanJson();
        boolean differ = !AutoBeanUtils.deepEquals(formBean, original);
        boolean isDirty = driver.isDirty();
        if (differ != isDirty) json += "\n Something weird.  isDirty != differ";
        // TODO: Hmmm.... looks like isDirty is not doing a deep check.  Do I need to manually assemble an 'Editor Hierarchy'?

        if (isDirty) {
            driver.flush();
            if (driver.hasErrors()) {
                String msg = "";
                for (EditorError e: driver.getErrors()) {
                    msg += e.getMessage();
                }
                Info.display("Error", msg);
            } else {
                String msg = "Finished editting...\n" + json;
                logger.info(msg);
                Info.display("Note", msg);
                dlg.hide((TextButton) event.getSource());
            }
        }
        else Info.display("Note", "No Changes\n" + json);
    }

    private String getFormBeanJson() {
        return AutoBeanCodex.encode(formBean).getPayload();
    }
}
