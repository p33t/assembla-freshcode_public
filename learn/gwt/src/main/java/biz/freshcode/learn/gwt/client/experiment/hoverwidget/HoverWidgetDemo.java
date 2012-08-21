package biz.freshcode.learn.gwt.client.experiment.hoverwidget;

import biz.freshcode.learn.gwt.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.experiment.hoverwidget.reuse.HoverWidgetSupport;
import biz.freshcode.learn.gwt.client.experiment.mouseover.reuse.MouseOverState;
import biz.freshcode.learn.gwt.client.experiment.mouseover.reuse.MultiMouseOverState;
import biz.freshcode.learn.gwt.client.uispike.builder.Construct;
import biz.freshcode.learn.gwt.client.uispike.builder.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt.client.util.DummySelectHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;

public class HoverWidgetDemo extends AbstractIsWidget {
    private HoverWidgetSupport<ToolButton> hoverSupp;
    private HoverWidgetSupport<HorizontalLayoutContainer> multiHover;
    private int ixMulti = -1;

    @Override
    protected Widget createWidget() {
        ToolButton hoverWidget = new ToolButton(ToolButton.SAVE, new DummySelectHandler("Saved"));
        final HtmlLayoutContainer simple = new HtmlLayoutContainer("<p>Hover Over This</p>");

        hoverSupp = new HoverWidgetSupport<ToolButton>(hoverWidget);

        new MouseOverState(simple, new MouseOverState.Callback() {
            @Override
            public void stateChange(MouseOverState mos) {
                if (mos.isDraggingOver()) hoverSupp.disablePopup();
                else if (mos.isOver()) {
                    Point coord = new Point(simple.getAbsoluteLeft(), simple.getAbsoluteTop());
                    hoverSupp.enablePopup(coord);
                } else hoverSupp.disablePopup();
            }
        });

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

        multiHover = new HoverWidgetSupport<HorizontalLayoutContainer>(new HorizontalLayoutContainerBuilder()
                .add(new ToolButton(ToolButton.MAXIMIZE, new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        Info.display("Event", "Clicked " + ixMulti);
                    }
                }))
                .horizontalLayoutContainer);

        new MultiMouseOverState(multi.iterator(), new MultiMouseOverState.Callback() {
            @Override
            public void stateChange(MouseOverState mos, int widgetIndex) {
                if (mos.isOver() && !mos.isDraggingOver()) {
                    Widget w = mos.getWidget();
                    Point p = new Point(w.getAbsoluteLeft(), w.getAbsoluteTop());
                    ixMulti = widgetIndex;
                    multiHover.enablePopup(p);
                } else {
                    // this could upset things if events not processed in correct order
                    multiHover.disablePopup();
                }
            }
        });


        return new VerticalLayoutContainerBuilder()
                .add(simple)
                .add(new HTMLPanel("<p>See also Mouse Over demo</p>"))
                .add(multi)
                .verticalLayoutContainer;
    }

}
