package biz.freshcode.qjug2011.util.trigger;

public interface ProxyProvider {
    <T> T proxy(Object obj, Class<T> iface);
}
