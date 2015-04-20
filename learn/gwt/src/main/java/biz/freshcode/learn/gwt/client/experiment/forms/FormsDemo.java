package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.DialogBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.button.TextButtonBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.util.AbstractIsWidget;
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

import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.newSetFrom;

public class FormsDemo extends AbstractIsWidget {
    // Keep this around so that changes can be accumulated.
    private AutoBean<FormBean> formBeanAuto = FormBean.FACTORY.auto();
    private AutoBean<PeriodBean> timeBeanAuto = PeriodBean.FACTORY.auto();

    {
        // Populate some default sub beans
        FormBean bean = formBeanAuto.as();
        bean.setStr("Default String");
        bean.setPreferredTimes(newSetFrom(AmPm.AM));
        List<FormBeanChild> subs = Util.createList();
        FormBeanChild sub = FormBeanChild.FACTORY.auto().as();
        sub.setKey(1);
        sub.setDt(new Date(0L));
        sub.setName("Epoch");
        sub.setStart(3600000L);
        sub.setDuration(60000L);
        subs.add(sub);

        sub = FormBeanChild.FACTORY.auto().as();
        sub.setKey(2);
        sub.setDt(new Date(1970, 12, 25));
        sub.setName("Xmas");
        sub.setStart(60000L);
        sub.setDuration(3600000L);
        subs.add(sub);

        bean.setChildren(subs);
    }

    @Override
    protected Widget createWidget() {
        TextButton btnForm;
        TextButton btnFormOutput;
        TextButton btnTime;
        TextButton btnTimeOutput;
        FlowLayoutContainer ctr = new FlowLayoutContainerBuilder()
                .add(btnForm = new TextButtonBuilder()
                        .text("Bean Edit")
                        .textButton)
                .add(btnFormOutput = new TextButtonBuilder()
                        .text("Output Contents")
                        .textButton)
                .add(new HTMLPanel("<br/>"))
                .add(btnTime = new TextButtonBuilder()
                        .text("Period Edit")
                        .textButton)
                .add(btnTimeOutput = new TextButtonBuilder()
                        .text("Output Contents")
                        .textButton)
                .flowLayoutContainer;

        btnForm.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                FormDialog dialog = new FormDialog();
                dialog.asWidget().addHideHandler(new HideEvent.HideHandler() {
                    public void onHide(HideEvent event) {
                        outputContents(formBeanAuto);
                    }
                });
                dialog.edit(formBeanAuto.as());
            }
        });

        btnFormOutput.addSelectHandler(new SelectEvent.SelectHandler() {
            public void onSelect(SelectEvent event) {
                outputContents(formBeanAuto);
            }
        });

        btnTime.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                PeriodDialog d = new PeriodDialog();
                d.edit(timeBeanAuto);
            }
        });

        btnTimeOutput.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                outputContents(timeBeanAuto);
            }
        });

        return ctr;
    }

    private void outputContents(AutoBean<?> auto) {
        String json = AutoBeanCodex.encode(auto).getPayload();
        Dialog result = new DialogBuilder()
                .title("Result")
                .widget(new HTMLPanel("<p>Currently...</p><p>" + SafeHtmlUtils.htmlEscape(json) + "</p>"))
                .hideOnButtonClick(true)
                .dialog;
        result.show();
    }
}
