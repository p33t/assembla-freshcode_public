package biz.freshcode.learn.gwt.server;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.newList;
import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.newMap;

public class Lightbulb {
    private Boolean isOn;
    private String id;
    private int version = 0;

    private static Map<String, Boolean> db = newMap();

    static {
        newBulb(true);
        newBulb(false);
    }

    /**
     * Returns ALL the lightbulb objects.
     *
     * @return
     */
    public static List<Lightbulb> findAll() {
        List<Lightbulb> l = newList();
        for (Map.Entry<String, Boolean> m : db.entrySet()) {
            Lightbulb lb = new Lightbulb();
            lb.setId(m.getKey());
            lb.setIsOn(m.getValue());
            l.add(lb);
        }
        return l;
    }

    // TODO: Why?  How can I just call implicitly needed one.
    public static Lightbulb find(String id) {
        return findLightbulb(id);
    }

    private static void newBulb(Boolean isOn) {
        String id = UUID.randomUUID().toString();
        db.put(id, isOn);
    }

//      Implicitly needed
    public static Lightbulb findLightbulb(String id) {
        Boolean isOn = db.get(id);
        if (isOn == null) return null;
        Lightbulb l = new Lightbulb();
        l.setId(id);
        l.setIsOn(isOn);
        return l;
    }

    public Boolean getIsOn() {
        return isOn;
    }

    public void setIsOn(Boolean on) {
        isOn = on;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
