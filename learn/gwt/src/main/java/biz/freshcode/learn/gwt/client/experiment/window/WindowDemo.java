package biz.freshcode.learn.gwt.client.experiment.window;

import biz.freshcode.learn.gwt.client.EntryPoint;
import biz.freshcode.learn.gwt.client.IsRootContent;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt2.common.client.builder.gwt.HTMLPanelBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.DialogBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.PopupBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.WindowBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.Popup;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import static biz.freshcode.learn.gwt.client.experiment.window.Bundle.STYLE;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * It seems that the combination of the following will have an adequate result:
 * - New instance of Window or Dialog each time (Don't forget a 'removeFromParent()' on old refs.
 * - FlowLayoutContainer as first widget to get scrolling
 * - Show before position
 */
public class WindowDemo extends AbstractIsWidget implements IsRootContent {
    private TextButton btnDialog;

    // Where is 'onEsc'?... seems to work without it here but not in other apps?
    private Dialog dialog = new DialogBuilder()
            .headingHtml("<p>Heading</p>")
            .widget(new HTMLPanel("<p>This guy has buttons support... otherwise same as 'Window'and<br/>some<br/>extra<br/>lines<br/>thanks</p>"))
            .predefinedButtons()
//            .hideOnButtonClick(true)
            .autoHide(true)
            .dialog;
    private Window window = new WindowBuilder()
            .headingHtml("<p>Heading</p>")
            .widget(new HTMLPanel("<p>Window Contentsand<br/>some<br/>extra<br/>lines<br/>thanks</p>"))
            .autoHide(true)
//            .constrain(false)  ... does nothing
//            .deferHeight(true) ... does nothing
            .window;
    private TextButton btnWindow;
    private TextButton btnPopup;
    private Popup popup = new PopupBuilder()
            .widget(new HTMLPanel("<p>Popup has no heading<br/>and<br/>some<br/>extra<br/>lines<br/>thanks</p>"))
            .borders(true)
//            .animate(true) // causes auto hiding problems for this and 'dialog' (?!)
            .title("The Title")
            .toolTip("The Tooltip")
            .addStyleName(STYLE.whiteBgnd())
            .popup;

    @Override
    protected Widget createWidget() {
        return new BorderLayoutContainerBuilder()
                .centerWidget(new HTMLPanelBuilder("<div style='width:100%; height:100%;'>&nbsp</div>")
                        .addStyleName(STYLE.bgndTile())
                        .hTMLPanel)
                // Easiest way to put buttons at bottom right of screen
                .southWidget(new BorderLayoutContainerBuilder()
                        .eastWidget(new HorizontalLayoutContainerBuilder()
                                .add(btnWindow = new TextButton("Show Window", new SelectEvent.SelectHandler() {
                                    @Override
                                    public void onSelect(SelectEvent event) {
                                        positionAndShow(window, btnWindow);
                                    }
                                }))
                                .add(btnDialog = new TextButton("Show Dialog", new SelectEvent.SelectHandler() {
                                    @Override
                                    public void onSelect(SelectEvent event) {
                                        positionAndShow(dialog, btnDialog);
                                    }
                                }))
                                .add(btnPopup = new TextButton("Show Popup", new SelectEvent.SelectHandler() {
                                    @Override
                                    public void onSelect(SelectEvent event) {
                                        popup.showAt(btnPopup.getAbsoluteLeft(), btnPopup.getAbsoluteTop());
                                    }
                                }))
                                .horizontalLayoutContainer, new BorderLayoutContainer.BorderLayoutData(220))
                        .borderLayoutContainer, new BorderLayoutContainer.BorderLayoutData(30))
                .borderLayoutContainer;
    }

    private void positionAndShow(Window window, TextButton orient) {
        // NOTE: Need to show before position
        // Otherwise positioning doesn't work after the first time
        window.show();
        position(window, orient);
    }

    private void position(Window w, Widget orient) {
        int left = orient.getAbsoluteLeft();
        int top = orient.getAbsoluteTop();

        GWT.log("Orientation position: " + left + ", " + top);

        RootLayoutPanel root = EntryPoint.getRoot();
        left = adjustPosition(left, w.getOffsetWidth(), root.getOffsetWidth());
        top = adjustPosition(top, w.getOffsetHeight(), root.getOffsetHeight());

        GWT.log("Showing at " + left + "," + top +
                "\n Visible:" + w.isVisible() + ", attached:" + w.isAttached());

//        Always 0,0 ?!
//        XElement elem = w.getElement();
//        GWT.log("Resulting position: " + elem.getAbsoluteLeft() + "," + elem.getAbsoluteTop());

        // This appears to only work the first time ?! (same for setPosition) !!!!!!!!!!!!!!!!!!!!!!!!!!
        // Unless it happens while the window is visible ?!
        w.setPagePosition(left, top);

// Always 0,0!!    GWT.log("Resulting position " + w.getAbsoluteLeft() + ", " + w.getAbsoluteTop());
// No help...      w.forceLayout();
    }

    private int adjustPosition(int desired, int windowSize, int displaySize) {
        return min(desired, max(0, displaySize - windowSize));
    }
}
