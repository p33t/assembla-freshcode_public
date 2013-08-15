package pckg_2_10.fixture;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AnIterable implements Iterable<String> {
    private List<String> list = new ArrayList<String>();

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
