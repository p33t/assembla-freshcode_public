package biz.freshcode.learn.gwt.mod1.client.experiment.forms;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import java.util.Date;

interface ChildAccess extends PropertyAccess<FormBeanChild> {
    ChildAccess INSTANCE = GWT.create(ChildAccess.class);

    // Use an immutable key value instead to rule out strange errors
    @Editor.Path("key")
    ModelKeyProvider<FormBeanChild> id();

    ValueProvider<FormBeanChild, Integer> key();

    ValueProvider<FormBeanChild, String> name();

    ValueProvider<FormBeanChild, Date> dt();

    ValueProvider<FormBeanChild, Long> start();

    ValueProvider<FormBeanChild, Long> duration();

    // NOTE: Don't use this interface to provide generated / calculated values.  See FinishProvider.
}
