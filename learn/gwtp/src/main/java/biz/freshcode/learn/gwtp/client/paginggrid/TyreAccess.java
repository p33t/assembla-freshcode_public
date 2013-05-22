package biz.freshcode.learn.gwtp.client.paginggrid;

import biz.freshcode.learn.gwtp.shared.paginggrid.Tyre;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface TyreAccess extends PropertyAccess<Tyre> {
    ModelKeyProvider<Tyre> id();

    ValueProvider<Tyre, Integer> diameter();

    ValueProvider<Tyre, Integer> thickness();
}
