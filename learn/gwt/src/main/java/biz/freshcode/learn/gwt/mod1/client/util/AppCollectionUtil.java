package biz.freshcode.learn.gwt.mod1.client.util;

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


    public static <T> List<T> newListFrom(T... ts) {
        List<T> l = newList();
        Collections.addAll(l, ts);
        return l;
    }

    public static <T> List<T> newListFrom(Collection<T> ts) {
        List<T> l = newList();
        l.addAll(ts);
        return l;
    }

    public static <K, V> Map<K, V> newMap() {
        return new HashMap<K, V>();
    }

    public static <K, V> Map<K, V> newMapFrom(Map<K, V> src) {
        return new HashMap<K, V>(src);
    }

    public static <T> T firstElem(Iterable<T> es) {
        return es.iterator().next();
    }
}
