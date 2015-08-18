package biz.freshcode.learn.gwt_bootstrap.client.builder;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.html.Paragraph;
import org.gwtbootstrap3.client.ui.html.Text;

/**
 * Custom builder for simple content.
 */
public class ParagraphSupport extends Paragraph {

    public static ParagraphSupport p() {
        return new ParagraphSupport();
    }

    public static ParagraphSupport p(String txt) {
        return p().txt(txt);
    }

    public static ParagraphSupport p(String text, String historyToken) {
        return p().a(text, historyToken);
    }

    public ParagraphSupport a(String text, String historyToken) {
        Anchor a = new Anchor();
        a.setText(text);
        a.setTargetHistoryToken(historyToken);
        add(a);
        return this;
    }

    public ParagraphSupport txt(String text) {
        add(new Text(text));
        return this;
    }
}
