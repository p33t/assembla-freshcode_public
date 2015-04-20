package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.DialogBuilder;
import biz.freshcode.learn.gwt2.common.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.sencha.gxt.widget.core.client.Dialog;

public class PeriodDialog extends AbstractIsWidget<Dialog> {
    interface Driver extends SimpleBeanEditorDriver<PeriodBean, PeriodBeanEditor> {
    }

    private Driver driver = GWT.create(Driver.class);    

    @Override
    protected Dialog createWidget() {
        PeriodBeanEditor editor;
        Dialog d = new DialogBuilder()
                .height(100)
                .width(400)
                .modal(true)
                .predefinedButtons()
                .add(editor = new PeriodBeanEditor())
                .dialog;
        driver.initialize(editor);
        ConfirmBeforeHideHandler.setup(driver, d);
        return d;
    }

    public void edit(AutoBean<PeriodBean> timeAuto) {
        Dialog w = asWidget();
        driver.edit(timeAuto.as());
        w.show();
    }
}
