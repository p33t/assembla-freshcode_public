package biz.freshcode.learn.gwt.client.experiment.forms;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import java.util.Date;

interface ChildAccess extends PropertyAccess<FormBeanChild> {
    ChildAccess INSTANCE = GWT.create(ChildAccess.class);

    // Use an immutable key value instead to rule out strange errors        @Path("name")
    ModelKeyProvider<FormBeanChild> key();

    ValueProvider<FormBeanChild, String> name();

    ValueProvider<FormBeanChild, Date> dt();
}
