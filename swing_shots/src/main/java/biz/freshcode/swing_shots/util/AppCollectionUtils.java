package biz.freshcode.swing_shots.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppCollectionUtils {
    public static <T> List<T> newList(T... elems) {
        ArrayList<T> l = new ArrayList<T>();
        Collections.addAll(l, elems);
        return l;
    }
}
