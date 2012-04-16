package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.uispike.builder.TextButtonBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.container.HtmlLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class Landing extends AbstractIsWidget {
    FormBean.Factory factory = GWT.create(FormBean.Factory.class);
    // Keep this around so that changes can be accumulated.
    private FormBean formBean = factory.create().as();

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
