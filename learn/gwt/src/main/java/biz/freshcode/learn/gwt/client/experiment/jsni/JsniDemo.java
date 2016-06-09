package biz.freshcode.learn.gwt.client.experiment.jsni;

import biz.freshcode.learn.gwt2.common.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;

import static com.google.gwt.safehtml.shared.SafeHtmlUtils.fromString;

public class JsniDemo extends AbstractIsWidget {

    @Override
    protected Widget createWidget() {
        return new HtmlLayoutContainer(fromString("Server time diff (Hopefully not '1234'):" + ServerTimeUtil.serverTimeDiff()));
    }
}
