package biz.freshcode.learn.gwt.mod1.client.experiment.requestfactory;

import biz.freshcode.learn.gwt.common.client.builder.gxt.button.TextButtonBuilder;
import biz.freshcode.learn.gwt.common.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.common.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.mod1.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import java.util.List;

public class RequestFactoryDemo extends AbstractIsWidget {
    HtmlLayoutContainer feedback;

    @Override
    protected Widget createWidget() {
        TextButton btnX;
        BorderLayoutContainer blc = new BorderLayoutContainerBuilder()
                .northWidget(new HorizontalLayoutContainerBuilder()
                        .add(btnX = new TextButtonBuilder()
                                .text("x")
                                .textButton)
                        .horizontalLayoutContainer)
                .centerWidget(feedback = new HtmlLayoutContainer("<p>Started</p>"))
                .borderLayoutContainer;
        btnX.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                x();
            }
        });
        return blc;
    }

    private void x() {
        msg("About to create factory");
        final ExperimentRequestFactory f = GWT.create(ExperimentRequestFactory.class);
        f.initialize(new SimpleEventBus());

        msg("Invoking findAll()");
        f.lightbulbRequest().findAll().fire(new Receiver<List<LightbulbProxy>>() {
            @Override
            public void onSuccess(List<LightbulbProxy> response) {
                msg("Successfully retrieved " + response.size() + " lightbulbs");
                LightbulbProxy lb0 = response.get(0);
                callFind(f, lb0);
            }
        });

    }

    private void callFind(ExperimentRequestFactory f, final LightbulbProxy lb0) {
        msg("Invoking find()");
        f.lightbulbRequest().find(lb0.getId()).fire(new Receiver<LightbulbProxy>() {
            @Override
            public void onSuccess(LightbulbProxy response) {
                msg("Result was " + response);
                if (response == lb0) msg("Same object instance");
                else if (response == null) msg("Not found");
                else if (lb0.equals(response)) msg("Equals objects");
                else msg("?! Not equal");
            }
        });
    }

    private void msg(String msg) {
        SafeHtml html = feedback.getHTML();
        SafeHtmlBuilder b = new SafeHtmlBuilder().append(html);
        b.appendHtmlConstant("<p>")
                .appendEscapedLines(msg)
                .appendHtmlConstant("</p>");
        feedback.setHTML(b.toSafeHtml());
    }
}
