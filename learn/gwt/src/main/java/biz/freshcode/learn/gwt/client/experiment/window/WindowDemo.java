package biz.freshcode.learn.gwt.client.experiment.window;

import biz.freshcode.learn.gwt.client.IsRootContent;
import biz.freshcode.learn.gwt.client.builder.gwt.HTMLPanelBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.DialogBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.PopupBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.WindowBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.Popup;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import static biz.freshcode.learn.gwt.client.experiment.window.Bundle.STYLE;

public class WindowDemo extends AbstractIsWidget implements IsRootContent {
    private TextButton btnDialog;
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
                .southWidget(new HorizontalLayoutContainerBuilder()
                        .add(btnWindow = new TextButton("Show Window", new SelectEvent.SelectHandler() {
                            @Override
                            public void onSelect(SelectEvent event) {
                                position(window, btnWindow);
                                window.show();
                            }
                        }))
                        .add(btnDialog = new TextButton("Show Dialog", new SelectEvent.SelectHandler() {
                            @Override
                            public void onSelect(SelectEvent event) {
                                position(dialog, btnDialog);
                                dialog.show();
                            }
                        }))
                        .add(btnPopup = new TextButton("Show Popup", new SelectEvent.SelectHandler() {
                            @Override
                            public void onSelect(SelectEvent event) {
                                popup.showAt(btnPopup.getAbsoluteLeft(), btnPopup.getAbsoluteTop());
                            }
                        }))
                        .horizontalLayoutContainer, new BorderLayoutContainer.BorderLayoutData(30))
                .borderLayoutContainer;
    }

    private void position(Window w, Widget orient) {
        GWT.log("Showing at " + orient.getAbsoluteLeft() + "," + orient.getAbsoluteTop());
        // This appears to only work the first time ?!
        w.setPagePosition(orient.getAbsoluteLeft(), orient.getAbsoluteTop());
//        Always 0,0 ?!
//        XElement elem = w.getElement();
//        GWT.log("Resulting position: " + elem.getAbsoluteLeft() + "," + elem.getAbsoluteTop());
    }

}
