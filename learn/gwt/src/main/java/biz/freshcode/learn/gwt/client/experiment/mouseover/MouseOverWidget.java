package biz.freshcode.learn.gwt.client.experiment.mouseover;

import biz.freshcode.learn.gwt.client.experiment.dnd.dragdata.DragData;
import biz.freshcode.learn.gwt.client.uispike.builder.HTMLPanelBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.ToolButtonBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.container.AbsolutePanelBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent;
import com.sencha.gxt.dnd.core.client.DragSource;
import com.sencha.gxt.dnd.core.client.DropTarget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;

import static biz.freshcode.learn.gwt.client.experiment.mouseover.Bundle.STYLE;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newSetFrom;

public class MouseOverWidget extends AbstractIsWidget {
    private static final SelectEvent.SelectHandler GO_HANDLER = new SelectEvent.SelectHandler() {
        public void onSelect(SelectEvent event) {
            Info.display("Event", "Go pushed");
        }
    };
    private ToolButton btnGo;
    private MouseOverState mosGo;
    private HTMLPanel targetWidget;
    private MouseOverState mosTarget;
    private UpdateState updateState = new UpdateState();
    // Update the state after events in queue are processed
    private Runnable updateStateDeferred = new Runnable() {
        @Override
        public void run() {
            Scheduler.get().scheduleDeferred(updateState);
        }
    };

    @Override
    protected Widget createWidget() {
        HTMLPanel draggable;

        VerticalLayoutContainer vlc = new VerticalLayoutContainerBuilder()
                .add(draggable = new HTMLPanelBuilder("<p>Drag this</p>")
                        .addStyleName(STYLE.blackBorder())
                        .hTMLPanel)
                .add(new HTMLPanel("<p>&nbsp;</p>"))
                .add(new TextButton("Test Go", GO_HANDLER))
                .add(new AbsolutePanelBuilder()
                        .add(targetWidget = new HTMLPanelBuilder("<p>Mouse over me!</p>")
                                .addStyleName(STYLE.blackBorder())
                                .hTMLPanel, 0, 0)
                        .add(btnGo = new ToolButtonBuilder(new ToolButton(ToolButton.SEARCH, GO_HANDLER))
                                .visible(false)
                                .toolButton, 0, 0)
                        // Hmmm.... sizing dramas.... ignoring container size :(
                        .width("100%")
                        .height("2em")
                        .absolutePanel)
                .verticalLayoutContainer;

        // setup dragging
        new DragSource(draggable).addDragStartHandler(new DndDragStartEvent.DndDragStartHandler() {
            @Override
            public void onDragStart(DndDragStartEvent event) {
                DragData.setup(event, String.class, newSetFrom("A String"));
            }
        });

        // NOTE: Dnd for GWT is separate to Dnd for GXT.

        mosGo = new MouseOverState(btnGo, updateStateDeferred);
        DropTarget dropTarget = new DropTarget(targetWidget);
        dropTarget.setOverStyle(STYLE.dragOver());
        mosTarget = new MouseOverState(dropTarget, updateStateDeferred);

        return vlc;
    }

    private void mouseOver() {
        targetWidget.addStyleName(STYLE.mouseOver());
        btnGo.setVisible(true);
    }


    private void unMouseOver() {
        targetWidget.removeStyleName(STYLE.mouseOver());
        btnGo.setVisible(false);
    }

    class UpdateState implements Scheduler.ScheduledCommand {
        @Override
        public void execute() {
            if (mosTarget.isOver() && !mosTarget.isDraggingOver() ||
                    mosGo.isOver() && !mosGo.isDraggingOver()) {
                if (!btnGo.isVisible()) mouseOver();
            } else {
                if (btnGo.isVisible()) unMouseOver();
            }
        }
    }
}
