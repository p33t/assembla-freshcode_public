package biz.freshcode.learn.gwt.mod1.client.experiment.hoverwidget.reuse;

import biz.freshcode.learn.gwt.common.client.builder.gwt.PopupPanelBuilder;
import biz.freshcode.learn.gwt.mod1.client.experiment.mouseover.reuse.MouseOverState;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Point;

import static biz.freshcode.learn.gwt.mod1.client.experiment.hoverwidget.reuse.Bundle.STYLE;

/**
 * Manages popping up a widget at a particular screen position.
 * The same widget can be used for different screen elements.
 *
 * TODO: Need an AbstractHoverWidget to complement this utility.
 */
public class HoverWidgetSupport<W extends IsWidget> {
    private static final int POSITION_OFFSET = 1;
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
     * Convenience method to tidy up code.
     */
    public static <W extends IsWidget> HoverWidgetSupport<W> hoverWidgetSupport(W hoverWidget) {
        return new HoverWidgetSupport<W>(hoverWidget);
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

    public void enablePopup(IsWidget w) {
        Widget widget = w.asWidget();
        enablePopup(new Point(widget.getAbsoluteLeft(), widget.getAbsoluteTop()));
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
        if (coord.equals(getPosition())) {
            // location is accurate (event may have been echoed)
            if (!popup.isShowing()) showPopup();
        } else {
            // different location
            hidePopupIfNecessary();
            setPosition(coord);
            showPopup();
        }
    }

    private void showPopup() {
        customizeHoverWidget(hoverWidget);
        popup.show();
    }

    private void hidePopupIfNecessary() {
        if (popup.isShowing()) {
            Point coord = getPosition();
            popup.hide();
            popupHidden(coord);
        }
    }

    private void setPosition(Point coord) {
        int x = coord.getX() + POSITION_OFFSET;
        int y = coord.getY() + POSITION_OFFSET;
        popup.setPopupPosition(x, y);
    }

    private Point getPosition() {
        int x = popup.getPopupLeft() - POSITION_OFFSET;
        int y = popup.getPopupTop() - POSITION_OFFSET;
        return new Point(x, y);
    }

    public W getHoverWidget() {
        return hoverWidget;
    }
}
