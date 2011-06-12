package biz.freshcode.swing_shots.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppCollectionUtils {
    public static <T> List<T> newList() {
        return new ArrayList<T>();
    }

    public static <T> List<T> newListFrom(T... elems) {
        List<T> l = newList();
        Collections.addAll(l, elems);
        return l;
    }
}
