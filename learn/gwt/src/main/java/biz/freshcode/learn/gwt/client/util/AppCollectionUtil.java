package biz.freshcode.learn.gwt.client.util;

import java.util.*;

public class AppCollectionUtil {
    public static <T> Set<T> newSet() {
        return new HashSet<T>();
    }

    public static <T> Set<T> newSetFrom(Collection<T> coll) {
        Set<T> s = newSet();
        s.addAll(coll);
        return s;
    }

    public static <T> Set<T> newSetFrom(T... ts) {
        Set<T> s = newSet();
        Collections.addAll(s, ts);
        return s;
    }

    public static <T> List<T> newList() {
        return new ArrayList<T>();
    }

    public static <K,V> Map<K, V> newMap() {
        return new HashMap<K, V>();
    }
}