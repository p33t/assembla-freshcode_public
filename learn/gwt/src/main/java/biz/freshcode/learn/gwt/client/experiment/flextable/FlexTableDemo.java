package biz.freshcode.learn.gwt.client.experiment.flextable;

import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.button.TextButtonBuilder;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;

import static biz.freshcode.learn.gwt.client.experiment.flextable.Bundle.BUNDLE;
import static biz.freshcode.learn.gwt.client.experiment.flextable.Bundle.STYLE;
import static com.sencha.gxt.cell.core.client.ButtonCell.IconAlign;


/**
 * This doesn't resize nicely but that is more a problem with how our app is setup.
 * GXT all-the-way-down works fine.
 */
public class FlexTableDemo extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        FlexTable tbl = new FlexTable();
        tbl.setCellSpacing(50);
        button(tbl, 0, 0, "Zero");
        button(tbl, 2, 1, "Longish");
        button(tbl, 2, 2, "Two<br/>Two");

        VBoxLayoutContainer c = new VBoxLayoutContainer();
        c.setVBoxLayoutAlign(VBoxLayoutContainer.VBoxLayoutAlign.CENTER);
        c.setPack(BoxLayoutContainer.BoxLayoutPack.CENTER);
        c.add(tbl);
        return c;
    }

    private void button(FlexTable flexTable, int row, int col, String html) {
        TextButton b = new TextButtonBuilder()
                .hTML(html)
                .icon(BUNDLE.iconPng())
                .addStyleName(STYLE.discreteButton())
                .iconAlign(IconAlign.TOP)
                .textButton;
        flexTable.setWidget(row, col, b);
        flexTable.getFlexCellFormatter().setHorizontalAlignment(row, col, HasHorizontalAlignment.ALIGN_CENTER);
    }
}
