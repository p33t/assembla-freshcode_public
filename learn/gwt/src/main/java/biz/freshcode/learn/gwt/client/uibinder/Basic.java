package biz.freshcode.learn.gwt.client.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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

    {
        // This must come first to bind fields to instance variables
        initWidget(binder.createAndBindUi(this));
        logger.info("Initializing...");
        for (String trans : Arrays.asList("Ferry", "Train", "Bus")) {
            listBox.addItem(trans);
        }
        logger.info("Finished Initializing");
    }
}
