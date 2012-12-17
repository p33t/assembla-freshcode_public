package biz.freshcode.learn.gwt.client.experiment.popfield;

import biz.freshcode.learn.gwt.client.uispike.builder.TextButtonBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt.client.util.DummySelectHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class PopFieldDemo extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        TextButton btn = new TextButtonBuilder()
                .text("Pop up field")
                .textButton;
        btn.addSelectHandler(new DummySelectHandler("Popup now"));
        return btn;
    }
}
