package biz.freshcode.swing_shots.config;

public class SysProp {
    public final String name;

    public SysProp(String name) {
        this.name = name;
    }

    public String get() {
        return System.getProperty(name);
    }

    public void set(String value) {
        System.setProperty(name, value);
    }
}
