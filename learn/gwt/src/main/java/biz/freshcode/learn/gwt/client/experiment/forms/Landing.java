package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.uispike.builder.DialogBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.TextButtonBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.sencha.gxt.core.client.util.Util;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import java.util.Date;
import java.util.List;

public class Landing extends AbstractIsWidget {
    FormBean.Factory factFormBean = GWT.create(FormBean.Factory.class);
    FormBeanSub.Factory factFormBeanSub = GWT.create(FormBeanSub.Factory.class);
    // Keep this around so that changes can be accumulated.
    private AutoBean<FormBean> formBeanAuto = factFormBean.create();

    {
        // Populate some default sub beans
        FormBean bean = formBeanAuto.as();
        List<FormBeanSub> subs = Util.createList();
        FormBeanSub sub = factFormBeanSub.create().as();
        sub.setKey(1);
        sub.setDt(new Date(0L));
        sub.setName("Epoch");
        subs.add(sub);

        sub = factFormBeanSub.create().as();
        sub.setKey(2);
        sub.setDt(new Date(1970, 12, 25));
        sub.setName("Xmas");
        subs.add(sub);

        bean.setSubs(subs);
    }

    @Override
    protected Widget createWidget() {
        TextButton btnDialog;
        TextButton btnOutput;
        FlowLayoutContainer ctr = new FlowLayoutContainerBuilder()
                .add(btnDialog = new TextButtonBuilder()
                        .text("Object Graph Edit")
                        .textButton)
                .add(btnOutput = new TextButtonBuilder()
                        .text("Output Contents")
                        .textButton)
                .flowLayoutContainer;

        btnDialog.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                FormDialog dialog = new FormDialog();
                dialog.asWidget().addHideHandler(new HideEvent.HideHandler() {
                    public void onHide(HideEvent event) {
                        outputContents();
                    }
                });
                dialog.edit(formBeanAuto.as());
            }
        });

        btnOutput.addSelectHandler(new SelectEvent.SelectHandler() {
            public void onSelect(SelectEvent event) {
                outputContents();
            }
        });
        return ctr;
    }

    private void outputContents() {
        String json = AutoBeanCodex.encode(formBeanAuto).getPayload();
        Dialog result = new DialogBuilder()
                .title("Result")
                .widget(new HTMLPanel("<p>Finished editing...</p><p>" + SafeHtmlUtils.htmlEscape(json) + "</p>"))
                .hideOnButtonClick(true)
                .dialog;
        result.show();
    }
}
