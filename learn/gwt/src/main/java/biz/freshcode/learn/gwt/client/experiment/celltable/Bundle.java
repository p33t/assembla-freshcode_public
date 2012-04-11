package biz.freshcode.learn.gwt.client.experiment.celltable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface Bundle extends ClientBundle {
    Bundle INSTANCE = GWT.create(Bundle.class);
    // Need to make sure style is injected into module
    boolean DOM_MUTATED = INSTANCE.style().ensureInjected();

    @Source("style.css")
    Style style();

    interface Style extends CssResource {
        String highlight();
    }
}
