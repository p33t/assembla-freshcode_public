package biz.freshcode.learn.gwtp.util.shared;

import java.util.*;

public class AppCollectionUtil {
    public static <T> Set<T> newSet() {
        return new HashSet<>();
    }

    public static <T> Set<T> newSetFrom(Collection<T> coll) {
        Set<T> s = newSet();
        s.addAll(coll);
        return s;
    }

    @SafeVarargs
    public static <T> Set<T> newSetFrom(T... ts) {
        Set<T> s = newSet();
        Collections.addAll(s, ts);
        return s;
    }

    public static <T> List<T> newList() {
        return new ArrayList<>();
    }


    @SafeVarargs
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

    public static <K,V> Map<K, V> newMap() {
        return new HashMap<>();
    }

    public static <T> T firstElem(Iterable<T> es) {
        if (es instanceof List) return ((List<T>) es).get(0);
        return es.iterator().next();
    }

    public static <K, V> Map<K, V> newOrderedMap() {
        return new LinkedHashMap<>();
    }

    public static <K, V> MapBuilder<K, V> newMapBuilder(K k0, V v0) {
        return new MapBuilder<K, V>()
                .put(k0, v0);
    }

    /**
     * Helps construction of hard-coded maps.
     */
    public static class MapBuilder<K, V> {
        public final Map<K, V> map = newMap();

        public MapBuilder<K, V> put(K key, V value) {
            map.put(key, value);
            return this;
        }
    }
}
