package biz.freshcode.learn.gwt.mod1.client.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import java.util.Arrays;
import java.util.logging.Logger;

// NOTE: Using ResizeComposite here breaks the UI.
// Something about wrapped widget not implementing RequiresResize (?)
public class Basic extends Composite {
    Logger logger = Logger.getLogger(getClass().getName());

    interface Binder extends UiBinder<Widget, Basic> {
    }

    private static Binder binder = GWT.create(Binder.class);
    @UiField
    ListBox listBox;
    // This one is supplied manually.  There are @UiFactory and @UiConstructor alternatives.
    @UiField(provided=true)
    ListBox randomsListBox;
    @UiField
    Button button;

    {
        randomsListBox = new ListBox(false);
        for (int i = 0; i < 10; i++) {
            randomsListBox.addItem("s-" + Random.nextInt());
        }
        // This must come first to bind non-provided fields to instance variables
        initWidget(binder.createAndBindUi(this));
        logger.info("Initializing...");
        for (String trans : Arrays.asList("Ferry", "Train", "Bus")) {
            listBox.addItem(trans);
        }
        logger.info("Finished Initializing");
    }

    @UiHandler("button")
    void handleClick(ClickEvent e) {
        Window.alert("Woo Hoo!");
    }

    @UiHandler("listBox")
    void handleChange(ChangeEvent e) {
        logger.info("listBox change " + System.currentTimeMillis());
    }
}
