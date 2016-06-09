package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.DialogBuilder;
import biz.freshcode.learn.gwt2.common.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.Dialog;

import java.util.logging.Logger;

public class FormDialog extends AbstractIsWidget<Dialog> {
    Logger logger = Logger.getLogger(getClass().getName());

    interface Driver extends SimpleBeanEditorDriver<FormBean, FormBeanEditor> {
    }

    private Driver driver = GWT.create(Driver.class);

    @Override
    protected Dialog createWidget() {
        logger.info("Creating widget");
//        TextButton btnClose;
        FormBeanEditor editor;
        final Dialog dlg = new DialogBuilder()
                .heading("Forms Demo")
                .width(500)
//                .height(300)
                .add(editor = new FormBeanEditor())
                // The predefined buttons are a little useless.  You have to dig them out again to define handlers (?)
                .predefinedButtons()
                .modal(true)
                .dialog;

        // Initialize editing
        logger.info("Driver initializing.");
        driver.initialize(editor);

        ConfirmBeforeHideHandler.setup(driver, dlg);
        return dlg;
    }

    public void edit(final FormBean formBean) {
        Dialog w = asWidget();
        logger.info("Driver about to edit.");
        driver.edit(formBean);
        // NOTE: This call does block despite .setModal(true).
        w.show();
    }
}
