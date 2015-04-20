package biz.freshcode.learn.gwt.client.experiment.mvp.homebake;

import biz.freshcode.learn.gwt2.common.client.util.AbstractIsWidget;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;

public class HbCenter extends AbstractIsWidget {
    private HtmlLayoutContainer hlc;

    public HbCenter() {
    }

    @Override
    protected Widget createWidget() {
        return hlc = new HtmlLayoutContainer("<p>Nothing</p>");
    }

    public void setHtml(String s) {
        hlc.setHTML(SafeHtmlUtils.htmlEscape(s));
    }
}
