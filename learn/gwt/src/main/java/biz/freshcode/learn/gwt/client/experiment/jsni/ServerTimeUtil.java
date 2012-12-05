package biz.freshcode.learn.gwt.client.experiment.jsni;

public class ServerTimeUtil {
    public static native int serverTimeDiff() /*-{
        if ("number" == typeof $wnd.serverTimeDiff) return $wnd.serverTimeDiff;
        return 1234; // testing value
    }-*/;
}
