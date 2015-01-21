package biz.freshcode.learn.gwt.mod1.client.bug.dateaccessbug;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import java.util.Date;

public interface DateBeanAccess extends PropertyAccess<DateBean> {
    DateBeanAccess INSTANCE = GWT.create(DateBeanAccess.class);

    ModelKeyProvider<DateBean> id();

    ValueProvider<DateBean, Date> dt();

}
