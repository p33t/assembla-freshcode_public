package biz.freshcode.learn.gwt.client.experiment.forms;

import com.google.gwt.editor.client.EditorDriver;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.user.client.Window;
import com.sencha.gxt.widget.core.client.event.BeforeHideEvent;

import java.util.List;

public class ConfirmBeforeHideHandler implements BeforeHideEvent.BeforeHideHandler {
    private EditorDriver driver;

    public static void setup(EditorDriver driver, BeforeHideEvent.HasBeforeHideHandlers hideable) {
        ConfirmBeforeHideHandler handler = new ConfirmBeforeHideHandler(driver);
        hideable.addBeforeHideHandler(handler);
    }


    public ConfirmBeforeHideHandler(EditorDriver driver) {
        this.driver = driver;
    }

    @Override
    public void onBeforeHide(BeforeHideEvent event) {
        driver.flush();
        if (driver.hasErrors()) {
            String msg = "";
            List<EditorError> errors = driver.getErrors();
            for (EditorError e : errors) {
                msg += "\n" + e.getMessage();
            }
            boolean ignore = Window.confirm("Ignore errors?\n" + msg);
            if (!ignore) event.setCancelled(true);
        }
    }
}
