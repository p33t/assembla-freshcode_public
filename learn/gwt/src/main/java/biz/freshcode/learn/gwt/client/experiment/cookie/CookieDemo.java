package biz.freshcode.learn.gwt.client.experiment.cookie;

import biz.freshcode.learn.gwt.client.GreetingServiceAsync;
import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.toolbar.ToolBarBuilder;
import biz.freshcode.learn.gwt.client.util.IsWidgetImpl;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.TextArea;

import java.util.Date;

public class CookieDemo extends IsWidgetImpl {
    private static final String COOK_E = "COOK-E";
    private final TextArea feedback;

    public CookieDemo() {
        initWidget(new BorderLayoutContainerBuilder()
                .northWidget(new ToolBarBuilder()
                        .add(new TextButton("Set Cookie", new SelectEvent.SelectHandler() {
                            @Override
                            public void onSelect(SelectEvent event) {
                                setCookie();
                            }
                        }))
                        .add(new TextButton("RPC", new SelectEvent.SelectHandler() {
                            @Override
                            public void onSelect(SelectEvent event) {
                                rpc();
                            }
                        }))
                        .toolBar)
                .centerWidget(feedback = new TextArea())
                .borderLayoutContainer);
    }

    private void rpc() {
        GreetingServiceAsync.Util.getInstance().readCookie(COOK_E, new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                msg("Failure: " + caught.getMessage());
            }

            @Override
            public void onSuccess(String result) {
                msg("Cookie read on server: " + result);
            }
        });
    }

    private void setCookie() {
        Date d = new Date();
        String value = "Cookie at " + d;
        CalendarUtil.addMonthsToDate(d, 12);
        Cookies.setCookie(COOK_E, value, d);
        msg("Cookie set to " + value);
    }

    private void msg(String msg) {
        feedback.setValue(feedback.getValue() + "\n" + msg);
    }
}
