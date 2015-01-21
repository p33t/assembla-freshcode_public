package biz.freshcode.learn.gwt.client.uispike;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;

public interface RowRenderer extends XTemplates {
    @XTemplate(source = "row.html")
    public SafeHtml render(Row row);
}
