package pkg.thread;

import java.util.logging.Logger;

class TestThread extends Thread {
    private final int offset;
    private int count = 1;

    static void log(String s) {
        Logger.getGlobal().info(s);
    }

    TestThread(int offset) {
        this.offset = offset;
    }

    static void sleepAwhile() {
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            // interrupted early
        }
    }

    void cycle() {
        output("" + count++);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            // do nothing.  This just shortens the sleep duration.
            output("interrupted");
        }
    }

    void output(String msg) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < offset; i++) sb.append("    ");
        sb.append(msg);
        log(sb.toString());
    }
}
