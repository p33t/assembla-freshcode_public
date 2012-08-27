package biz.freshcode.learn.gwt.client.experiment.window;

import biz.freshcode.learn.gwt.client.builder.gxt.DialogBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.PopupBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.WindowBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.Popup;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import static biz.freshcode.learn.gwt.client.experiment.window.Bundle.STYLE;

public class WindowDemo extends AbstractIsWidget {
    private TextButton btnDialog;
    private Dialog dialog = new DialogBuilder()
            .headingHtml("<p>Heading</p>")
            .widget(new HTMLPanel("<p>This guy has buttons support... otherwise same as 'Window'</p>"))
            .predefinedButtons(Dialog.PredefinedButton.CLOSE)
            .hideOnButtonClick(true)
            .autoHide(true)
            .dialog;
    private Window window = new WindowBuilder()
            .headingHtml("<p>Heading</p>")
            .widget(new HTMLPanel("<p>Window Contents</p>"))
            .autoHide(true)
//            .deferHeight(true) ... does nothing
            .window;
    private TextButton btnWindow;
    private TextButton btnPopup;
    private Popup popup = new PopupBuilder()
            .widget(new HTMLPanel("<p>Popup has no heading</p>"))
            .borders(true)
//            .animate(true) // causes auto hiding problems for this and 'dialog' (?!)
            .title("The Title")
            .toolTip("The Tooltip")
            .addStyleName(STYLE.whiteBgnd())
            .popup;

    @Override
    protected Widget createWidget() {
        return new HorizontalLayoutContainerBuilder()
                .add(btnDialog = new TextButton("Show Dialog", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        dialog.setPosition(btnDialog.getAbsoluteLeft(), btnDialog.getAbsoluteTop());
                        dialog.show();
                    }
                }))
                .add(btnWindow = new TextButton("Show Window", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        window.setPosition(btnWindow.getAbsoluteLeft(), btnWindow.getAbsoluteTop());
                        window.show();
                    }
                }))
                .add(btnPopup = new TextButton("Show Popup", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        popup.showAt(btnPopup.getAbsoluteLeft(), btnPopup.getAbsoluteTop());
                    }
                }))
                .horizontalLayoutContainer;
    }

}
