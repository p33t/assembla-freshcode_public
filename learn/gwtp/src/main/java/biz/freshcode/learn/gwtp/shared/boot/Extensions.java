package biz.freshcode.learn.gwtp.shared.boot;

import biz.freshcode.learn.gwtp.client.boot.AppMenuItem;
import biz.freshcode.learn.gwtp.client.ext.Extensible;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.List;

import static biz.freshcode.learn.gwtp.util.shared.AppCollectionUtil.newListFrom;

/**
 * Encapsulates the extensible aspects of the app.  This will be subclassed by special domains.
 */
public class Extensions implements IsSerializable {
    public static final Extensions VANILLA = new Extensions();

    public List<AppMenuItem> customMenuItems() {
        return newListFrom(new AppMenuItem("Extensible", Extensible.TOKEN));
    }
}
