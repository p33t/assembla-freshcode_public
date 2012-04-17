package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.uispike.builder.DialogBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.event.BeforeHideEvent;
import com.sencha.gxt.widget.core.client.info.Info;

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
                .headingText("Forms Demo")
                .width(500)
                .height(300)
                .add(editor = new FormBeanEditor())
                        // The predefined buttons are a litle useless.  You have to dig them out again to define handlers (?)
                .predefinedButtons(new Dialog.PredefinedButton[0])
                .modal(true)
                .dialog;

        // Initialize editing
        logger.info("Driver initializing.");
        driver.initialize(editor);

        dlg.addBeforeHideHandler(new BeforeHideEvent.BeforeHideHandler() {
            @Override
            public void onBeforeHide(BeforeHideEvent event) {
                // TODO: It seem that the ListStoreEdit has outstanding changes that are applied after the flush returns.
                // Scheduler.defer() doesn't work.
                driver.flush();
                if (driver.hasErrors()) {
                    String msg = "";
                    for (EditorError e : driver.getErrors()) {
                        msg += e.getMessage();
                    }
                    Info.display("Error", msg);
                    //TODO: This is dangerous without a 'Cancel' button
                    event.setCancelled(true);
                }

            }
        });

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
