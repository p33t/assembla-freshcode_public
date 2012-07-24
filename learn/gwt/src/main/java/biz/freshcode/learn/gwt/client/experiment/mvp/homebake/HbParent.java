package biz.freshcode.learn.gwt.client.experiment.mvp.homebake;

import biz.freshcode.learn.gwt.client.uispike.builder.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;

public class HbParent extends AbstractIsWidget<BorderLayoutContainer> implements HbNorth.Host {
    private HbNorth north;
    private HbCenter center;
    private int num;

    @Override
    protected BorderLayoutContainer createWidget() {
        BorderLayoutContainer blc = new BorderLayoutContainerBuilder()
                .northWidget(north = new HbNorth(this))
                .centerWidget(center = new HbCenter())
                .borderLayoutContainer;
        refresh();
        return blc;
    }

    @Override
    public void notifyAdvance() {
        num++;
        refresh();
    }

    public void refresh() {
        int num = this.num;
        String s = "";
        for (int i = 0; i < num; i++) s += "=";
        s += ">";
        center.setHtml(s);
        north.setButtonText("Advance to " + (num + 1));
    }
}
