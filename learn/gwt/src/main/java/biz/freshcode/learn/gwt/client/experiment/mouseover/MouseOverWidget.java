package biz.freshcode.learn.gwt.client.experiment.mouseover;

import biz.freshcode.learn.gwt.client.experiment.dnd.dragdata.DragData;
import biz.freshcode.learn.gwt.client.uispike.builder.HTMLPanelBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.dnd.core.client.DndDragEnterEvent;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent;
import com.sencha.gxt.dnd.core.client.DragSource;
import com.sencha.gxt.dnd.core.client.DropTarget;

import static biz.freshcode.learn.gwt.client.experiment.mouseover.Bundle.STYLE;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newSetFrom;
import static com.google.gwt.dom.client.NativeEvent.BUTTON_LEFT;

public class MouseOverWidget extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        HTMLPanel draggable = new HTMLPanelBuilder("<p>Drag this</p>")
                .addStyleName(STYLE.blackBorder())
                .hTMLPanel;
        new DragSource(draggable).addDragStartHandler(new DndDragStartEvent.DndDragStartHandler() {
            @Override
            public void onDragStart(DndDragStartEvent event) {
                DragData.setup(event, String.class, newSetFrom("A String"));
            }
        });

        final HTMLPanel targetWidget = new HTMLPanelBuilder("<p>Mouse over me!</p>")
                .addStyleName(STYLE.blackBorder())
                .hTMLPanel;

        targetWidget.addDomHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                GWT.log("Mouse over");
                // This does not work
                if ((event.getNativeButton() & BUTTON_LEFT) == 0) {
                    // left button is NOT down
                }
                targetWidget.addStyleName(STYLE.mouseOver());
            }
        }, MouseOverEvent.getType());
        targetWidget.addDomHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                GWT.log("Mouse out");
                targetWidget.removeStyleName(STYLE.mouseOver());
            }
        }, MouseOutEvent.getType());

        DropTarget target = new DropTarget(targetWidget);
        target.setOverStyle(STYLE.dragOver());
        target.addDragEnterHandler(new DndDragEnterEvent.DndDragEnterHandler() {
            @Override
            public void onDragEnter(DndDragEnterEvent event) {
                GWT.log("GXT Drag over");
                event.getStatusProxy().setStatus(false);
            }
        });
        targetWidget.addDomHandler(new DragOverHandler() {
            @Override
            public void onDragOver(DragOverEvent event) {
                // NOTE: This isn't showing.  GXT must not use native drag-drop.
                GWT.log("Native Drag over");
            }
        }, DragOverEvent.getType());

        return new VerticalLayoutContainerBuilder()
                .add(draggable)
                .add(new HTMLPanel("<p>&nbsp;</p>"))
                .add(targetWidget)
                .verticalLayoutContainer;
    }
}
