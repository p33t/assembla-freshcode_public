package biz.freshcode.learn.gwt.client.experiment.hoverwidget.reuse;

import biz.freshcode.learn.gwt.client.experiment.mouseover.reuse.MouseOverState;
import biz.freshcode.learn.gwt.client.uispike.builder.container.PopupPanelBuilder;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Point;

import static biz.freshcode.learn.gwt.client.experiment.hoverwidget.reuse.Bundle.STYLE;

/**
 * Manages popping up a widget at a particular screen position.
 * The same widget can be used for different screen elements.
 */
public class HoverWidgetSupport<W extends Widget> {
    protected final W hoverWidget;
    private final MouseOverState mosPopup;
    private final PopupPanel popup;
    private Point popupCoord;

    public HoverWidgetSupport(W hoverWidget) {
        this.hoverWidget = hoverWidget;
        popup = new PopupPanelBuilder()
                .addStyleName(STYLE.hoverWidgetPopup())
                .widget(hoverWidget)
                .popupPanel;
        mosPopup = new MouseOverState(popup, new MouseOverState.Callback() {
            @Override
            public void stateChange(MouseOverState mos) {
                checkPopup();
            }
        });
    }

    /**
     * Checks and updates the state of the popup.  This is designed to not repeat echo'd operations.
     * So the popup state can be jittery but will still display cleanly.
     */
    public void checkPopup() {
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                Point coord = popupCoord;
                if (mosPopup.isOver()) {
                    // mouse is currently over the popup
                    // the popup will probably be disabled by now but it must remain shown
                } else if (coord != null) {
                    // popup enabled
                    showPopupIfNecessary(coord);
                } else {
                    hidePopupIfNecessary();
                }
            }
        });
    }

    public void disablePopup() {
        popupCoord = null;
        checkPopup();
    }

    public void enablePopup(Point coord) {
        popupCoord = coord;
        checkPopup();
    }

    /**
     * Subclasses can customise the hoverWidget just before it is shown.
     */
    protected void customizeHoverWidget(W hoverWidget) {
        // do nothing by default
    }

    /**
     * Optional notification to subclasses.
     */
    protected void popupHidden(Point coord) {
        // nothing
    }

    private void showPopupIfNecessary(Point coord) {
        if (coord.equals(currentCoord())) {
            // location is accurate (event may have been echoed)
            if (!popup.isShowing()) showPopup();
        } else {
            // different location
            hidePopupIfNecessary();
            popup.setPopupPosition(coord.getX(), coord.getY());
            showPopup();
        }
    }

    private void showPopup() {
        customizeHoverWidget(hoverWidget);
        popup.show();
    }

    private void hidePopupIfNecessary() {
        if (popup.isShowing()) {
            Point coord = currentCoord();
            popup.hide();
            popupHidden(coord);
        }
    }

    private Point currentCoord() {
        return new Point(popup.getPopupLeft(), popup.getPopupTop());
    }
}
