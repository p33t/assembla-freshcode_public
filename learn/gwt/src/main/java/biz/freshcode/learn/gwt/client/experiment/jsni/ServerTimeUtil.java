package biz.freshcode.learn.gwt.client.experiment.jsni;

public class ServerTimeUtil {
    public static native int serverTimeDiff() /*-{
        var diff = $wnd.serverTimeDiff;
        if ("number" == typeof diff) return diff;
        return 1234; // testing value
    }-*/;
}
