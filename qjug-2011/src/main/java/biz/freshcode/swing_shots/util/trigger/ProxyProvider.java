package biz.freshcode.swing_shots.util.trigger;

public interface ProxyProvider {
    <T> T proxy(Object obj, Class<T> iface);
}
