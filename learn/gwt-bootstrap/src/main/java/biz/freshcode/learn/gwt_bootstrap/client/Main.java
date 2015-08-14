package biz.freshcode.learn.gwt_bootstrap.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import org.gwtbootstrap3.client.ui.html.Paragraph;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Main implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        // Use RootPanel.get() to get the entire body element
        RootPanel.get().add(new Paragraph("Hello World"));
    }
}
