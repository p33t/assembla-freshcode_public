package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.uispike.builder.DialogBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.sencha.gxt.widget.core.client.Dialog;

public class TimeDialog extends AbstractIsWidget<Dialog> {
    interface Driver extends SimpleBeanEditorDriver<TimeBean, TimeEditor> {
    }

    private Driver driver = GWT.create(Driver.class);    

    @Override
    protected Dialog createWidget() {
        TimeEditor editor;
        Dialog d = new DialogBuilder()
                .height(100)
                .width(200)
                .modal(true)
                .predefinedButtons()
                .add(editor = new TimeEditor())
                .dialog;
        driver.initialize(editor);
        ConfirmBeforeHideHandler.setup(driver, d);
        return d;
    }

    public void edit(AutoBean<TimeBean> timeAuto) {
        Dialog w = asWidget();
        driver.edit(timeAuto.as());
        w.show();
    }
}
