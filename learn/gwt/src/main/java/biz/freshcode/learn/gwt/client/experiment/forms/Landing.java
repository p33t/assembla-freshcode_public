package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.uispike.builder.TextButtonBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.sencha.gxt.core.client.util.Util;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import java.util.Date;
import java.util.List;

public class Landing extends AbstractIsWidget {
    FormBean.Factory factory = GWT.create(FormBean.Factory.class);
    // Keep this around so that changes can be accumulated.
    private AutoBean<FormBean> formBean = factory.create();

    {
        // Populate some default sub beans
        FormBean bean = formBean.as();
        List<FormBeanSub> subs = Util.<FormBeanSub>createList();
        FormBeanSub sub = new FormBeanSub();
        sub.setDt(new Date(0L));
        sub.setName("Epoch");
        subs.add(sub);
        bean.setSubs(subs);
    }

    @Override
    protected Widget createWidget() {
        TextButton btnDialog;
        FlowLayoutContainer ctr = new FlowLayoutContainerBuilder()
                .add(btnDialog = new TextButtonBuilder()
                        .text("Dialog Form")
                        .textButton)
                .flowLayoutContainer;
        btnDialog.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                new FormDialog(formBean).asWidget().show();
            }
        });
        return ctr;
    }
}
