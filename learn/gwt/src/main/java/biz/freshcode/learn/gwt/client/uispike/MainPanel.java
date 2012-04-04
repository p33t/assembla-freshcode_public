package biz.freshcode.learn.gwt.client.uispike;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;

import java.util.logging.Logger;

public class MainPanel extends Composite {
    private static Binder binder = GWT.create(Binder.class);
    Logger logger = Logger.getLogger(getClass().getName());

    {
        initWidget(binder.createAndBindUi(this));
    }

    interface Binder extends UiBinder<Widget, MainPanel> {
    }

    /**
     * Supplies fiddley ui configuration
     */
    static class UiConfig {
        BorderLayoutData westLayout() {
            BorderLayoutData b = new BorderLayoutData();
            b.setSize(100);
            b.setCollapsible(true);
            b.setSplit(true);
            b.setCollapseMini(true);
            b.setMargins(westMargins());
            return b;
        }

        Margins westMargins() {
//            Old Way
//            Margins m = new Margins();
//            m.setTop(0);
//            m.setRight(5);
//            m.setBottom(m.getTop());
//            m.setLeft(m.getRight());
//            return m;
            return new MarginsBuilder()
                    .top(0)
                    .right(5)
                    .bottom(0)
                    .left(5)
                    .margins;
        }
    }
}
