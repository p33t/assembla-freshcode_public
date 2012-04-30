package biz.freshcode.learn.gwt.client.experiment.forms;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.IdentityValueProvider;
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

/*
NOTE: This is not working.  Strange error indicating custom ValueProviders are not simple....
00:00:10.238 [ERROR] Errors in '/home/pleong/work/learn/gwt/target/.generated/biz/freshcode/learn/gwt/client/experiment/forms/FormBeanChild_finish_ValueProviderImpl.java'
00:00:10.238 [ERROR] Line 3: Implicit super constructor FinishProvider() is undefined for default constructor. Must define an explicit constructor
 */
    IdentityValueProvider<FormBeanChild> finish();
}
