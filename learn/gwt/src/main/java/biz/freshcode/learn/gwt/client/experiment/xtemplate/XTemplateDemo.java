package biz.freshcode.learn.gwt.client.experiment.xtemplate;

import biz.freshcode.learn.gwt2.common.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.XTemplates;

import java.util.List;

import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.newListFrom;

public class XTemplateDemo extends AbstractIsWidget {
    Renderer renderer = GWT.create(Renderer.class);

    @Override
    protected Widget createWidget() {
        Bean b = new Bean();
        b.setStr("3 Numbers");
        //b.field = "Fields Work!";
        b.setStrings(newListFrom("One", "Two", "Three"));
        return new HTMLPanel(renderer.templateXml(b));
    }

    public static class Bean {
        // Doesn't work...public String field;
        private String str;
        private List<String> strings;

        public List<String> getStrings() {
            return strings;
        }

        public void setStrings(List<String> strings) {
            this.strings = strings;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }

    public interface Renderer extends XTemplates {
        @XTemplates.XTemplate(source = "template.xml")
        SafeHtml templateXml(Bean bean);
    }
}
