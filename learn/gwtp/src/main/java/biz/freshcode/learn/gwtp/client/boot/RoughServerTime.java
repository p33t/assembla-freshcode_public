package biz.freshcode.learn.gwtp.client.boot;

import com.google.gwt.core.client.GWT;

/**
 *
 */
public class RoughServerTime {
    private static long serverDiff = 123;
    
    static void init(long localBootTime, long serverBootTime) {
        serverDiff = serverBootTime - localBootTime;
        GWT.log("Server time diff is " + serverDiff + " millisecs");
    }
    
    public long get() {
        return System.currentTimeMillis() + serverDiff;
    }
}
