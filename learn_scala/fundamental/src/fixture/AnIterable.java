package fixture;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Java {@link Iterable} class.
 */
public class AnIterable implements Iterable<String> {
    private final List<String> list = new ArrayList<>();

    {
        list.add("one");
        list.add("two");
        list.add("three");
    }


    @Override
    public Iterator<String> iterator() {
        return list.iterator();
    }
}
