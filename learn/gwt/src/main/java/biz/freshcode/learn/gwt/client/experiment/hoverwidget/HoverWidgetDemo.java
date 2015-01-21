package biz.freshcode.learn.gwt.client.experiment.hoverwidget;

import biz.freshcode.learn.gwt.client.experiment.hoverwidget.reuse.HoverWidgetSupport;
import biz.freshcode.learn.gwt.client.experiment.mouseover.reuse.MouseOverState;
import biz.freshcode.learn.gwt.client.experiment.mouseover.reuse.MultiMouseOverState;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt.client.util.DummySelectHandler;
import biz.freshcode.learn.gwt2.common.client.builder.Construct;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;

import static biz.freshcode.learn.gwt.client.experiment.hoverwidget.reuse.HoverWidgetSupport.hoverWidgetSupport;

/**
 * NOTE: It is also possible to hover over a snippet of SafeHtml by wrapping it in a div with an id.
 */
public class HoverWidgetDemo extends AbstractIsWidget {
    private HoverWidgetSupport<ToolButton> hoverSupp;
    private HoverWidgetSupport<ToolButton> multiHover;
    private int ixMulti = -1;

    @Override
    protected Widget createWidget() {
        return new VerticalLayoutContainerBuilder()
                .add(createSimple())
                .add(new HTMLPanel("<p>See also Mouse Over demo</p>"))
                .add(createMulti())
                .verticalLayoutContainer;
    }

    private HorizontalLayoutContainer createMulti() {
        // displayed components
        HorizontalLayoutContainer multi = new HorizontalLayoutContainerBuilder()
                .construct(new Construct<HorizontalLayoutContainerBuilder>() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            HTMLPanel hp = new HTMLPanel("<p>&lt;&lt;" + i + "&gt;&gt;&nbsp;&nbsp;</p>");
                            builder.add(hp);
                        }
                    }
                })
                .horizontalLayoutContainer;


        multiHover = hoverWidgetSupport(new ToolButton(ToolButton.MAXIMIZE,
                new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        Info.display("Event", "Clicked " + ixMulti);
                    }
                }));

        new MultiMouseOverState(multi.iterator(), new MultiMouseOverState.Callback() {
            @Override
            public void stateChange(MouseOverState mos, int widgetIndex) {
                if (mos.isHover()) {
                    ixMulti = widgetIndex;
                    multiHover.enablePopup(mos.getWidget());
                } else {
                    // this could upset things if events not processed in correct order
                    multiHover.disablePopup();
                }
            }
        });
        return multi;
    }

    private HtmlLayoutContainer createSimple() {
        ToolButton hoverWidget = new ToolButton(ToolButton.SAVE, new DummySelectHandler("Saved"));
        final HtmlLayoutContainer simple = new HtmlLayoutContainer("<p>Hover Over This</p>");

        hoverSupp = new HoverWidgetSupport<ToolButton>(hoverWidget);

        new MouseOverState(simple, new MouseOverState.Callback() {
            @Override
            public void stateChange(MouseOverState mos) {
                if (mos.isHover()) {
                    Point coord = new Point(simple.getAbsoluteLeft(), simple.getAbsoluteTop());
                    hoverSupp.enablePopup(coord);
                } else {
                    hoverSupp.disablePopup();
                }
            }
        });
        return simple;
    }

}
