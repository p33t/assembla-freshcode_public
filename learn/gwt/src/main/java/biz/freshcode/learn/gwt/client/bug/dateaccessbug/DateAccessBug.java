package biz.freshcode.learn.gwt.client.bug.dateaccessbug;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.DialogBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.sencha.gxt.core.client.util.Util;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.event.HideEvent;

import java.util.Date;

public class DateAccessBug extends AbstractIsWidget<Dialog> {
    interface Driver extends SimpleBeanEditorDriver<ParentBean, ParentBeanEditor> {
    }

    private Driver driver = GWT.create(Driver.class);

    @Override
    protected Dialog createWidget() {
        ParentBeanEditor editor;
        Dialog d = new DialogBuilder()
                .modal(true)
                .height(400)
                .width(500)
                .title("Date Access Bug")
                .hideOnButtonClick(true)
                .add(new FlowLayoutContainerBuilder()
                        .add(new HTMLPanel("<p>To reproduce error:<br/>" +
                                "- Click on the date below<br/>" +
                                "- Use the drop down date editor to change the date<br/>" +
                                "- Close the form (without blurring the cell)<br/>" +
                                "- Contrast this with editing the date by changing the string</p>"))
                        .add(editor = new ParentBeanEditor())
                        .flowLayoutContainer)
                .dialog;
        driver.initialize(editor);
        final ParentBean pb = epochParent();
        driver.edit(pb);
        d.addHideHandler(new HideEvent.HideHandler() {
            @Override
            public void onHide(HideEvent hideEvent) {
                driver.flush();
                DateBean dateBean = pb.getDates().get(0);
                display(dateBean);
            }
        });
        return d;
    }

    private void display(DateBean dateBean) {
        Dialog d = new DialogBuilder()
                .modal(true)
                .title("Result")
                .widget(new HTMLPanel("<p>" + SafeHtmlUtils.htmlEscape(dateBean.getDt().toString()) + "</p>"))
                .hideOnButtonClick(true)
                .dialog;
        d.show();
    }

    private ParentBean epochParent() {
        ParentBean pb = ParentBean.FACTORY.auto().as();
        DateBean epoch = DateBean.FACTORY.auto().as();
        epoch.setDt(new Date(0L));
        epoch.setId(1);
        pb.setDates(Util.createList(epoch));
        return pb;
    }
}
