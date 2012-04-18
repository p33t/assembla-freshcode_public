package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.uispike.builder.DialogBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.sencha.gxt.widget.core.client.Dialog;

public class TimeDialog extends AbstractIsWidget<Dialog> {
    @Override
    protected Dialog createWidget() {
        return new DialogBuilder()
                .height(100)
                .width(200)
                .modal(true)
                .predefinedButtons()
                .add(new HTMLPanel("<p>Hello</p>"))
                .dialog;
    }

    public void edit(AutoBean<TimeBean> timeAuto) {
        // TODO
        asWidget().show();
    }
}
