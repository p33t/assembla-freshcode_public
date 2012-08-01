package biz.freshcode.learn.gwt.client.experiment.xtemplate;

import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.XTemplates;

public class XTemplateDemo extends AbstractIsWidget {
    Renderer renderer = GWT.create(Renderer.class);

    @Override
    protected Widget createWidget() {
        return new HTMLPanel(renderer.templateXml());
    }

    public interface Renderer extends XTemplates {
        @XTemplates.XTemplate(source = "template.xml")
        SafeHtml templateXml();
    }
}
