package biz.freshcode.learn.gwt.client.experiment.mvp.gwtmvp;

import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import static com.google.gwt.safehtml.shared.SafeHtmlUtils.htmlEscape;

@Singleton
public class GmViewImpl extends AbstractIsWidget implements GmView {
    // NOTE: No state!... this should be instance reusable.
    private Presenter presenter;
    private TextButton btn;
    private HtmlLayoutContainer hlc;
    private Hyperlink link;

    @Override
    protected Widget createWidget() {
        return new BorderLayoutContainerBuilder()
                .northWidget(new HorizontalLayoutContainerBuilder()
                        .add(btn = new TextButton("???", new SelectEvent.SelectHandler() {
                            @Override
                            public void onSelect(SelectEvent event) {
                                presenter.notifyButtonPressed();
                            }
                        }))
                        .add(new CheckBox("Display State (remains when Back button used)"))
                        .add(link = new Hyperlink("Smaller", ""))
                        .horizontalLayoutContainer)
                .centerWidget(hlc = new HtmlLayoutContainer("<p>Nothing</p>"))
                .borderLayoutContainer;
    }

    @Override
    public void setButtonText(String s) {
        btn.setHTML(htmlEscape(s));
    }



    @Override
    public void setHtml(String s) {
        hlc.setHTML(htmlEscape(s));
    }

    @Override
    public void setPresenter(Presenter p) {
        // NOTE: To get view instance re-use all references to an old presenter must be updated here.
        presenter = p;
    }

    @Override
    public void setLinkTargetToken(String token) {
        link.setTargetHistoryToken(token);
    }
}
