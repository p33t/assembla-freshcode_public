package biz.freshcode.learn.gwt.client.experiment.flextable;

import biz.freshcode.learn.gwt.client.builder.gwt.FlexTableBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;


/**
 * This doesn't resize nicely but that is more a problem with how our app is setup.
 * GXT all-the-way-down works fine.
 */
public class FlexTableDemo extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        FlexTable tbl = new FlexTableBuilder()
                .widget(0, 0, btn())
                .widget(1, 1, btn())
                .widget(1, 2, btn())
                .widget(2, 1, btn())
                .widget(2, 2, btn())
                .flexTable;

        ToolBar tb = new ToolBar();
        tb.setPixelSize(300, 300);
        tb.add(tbl);

        VBoxLayoutContainer c = new VBoxLayoutContainer();
        c.setVBoxLayoutAlign(VBoxLayoutContainer.VBoxLayoutAlign.CENTER);
        c.setPack(BoxLayoutContainer.BoxLayoutPack.CENTER);
        c.add(tb);
        return c;
    }

    private TextButton btn() {
        TextButton b = new TextButton("Hello");
        b.setWidth(50);
        b.setHeight(50);
        return b;
    }
}
