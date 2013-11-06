package biz.freshcode.learn.gwtp.shared.boot;

import biz.freshcode.learn.gwtp.client.boot.AppMenuItem;
import biz.freshcode.learn.gwtp.client.special.Special;

import java.util.List;

import static biz.freshcode.learn.gwtp.shared.util.AppCollectionUtil.newListFrom;

public class SpecialExtensions extends Extensions {
    public static final SpecialExtensions SPECIAL = new SpecialExtensions();

    @Override
    public List<AppMenuItem> customMenuItems() {
        return newListFrom(new AppMenuItem("Special", Special.TOKEN));
    }
}
