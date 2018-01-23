package pkg.thread;

import java.util.logging.Logger;

class TestThread extends Thread {
    private final int offset;
    private int count = 1;

    TestThread(int offset) {
        this.offset = offset;
    }

    static void log(String s) {
        Logger.getGlobal().info(s);
    }

    static void sleepAwhile() {
        sleepFor(6000);
    }

    static void sleepFor(int millis) {
        try {
            sleep(millis);
        } catch (InterruptedException e) {
            // interrupted early
            // NOT: Catching this exception seems to clear any interrupt flags
            // rethrow without needing checked exception declarations in method signature
            Thread.currentThread().interrupt();
        }
    }

    void cycle() {
        outputCount();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            // do nothing.  This just shortens the sleep duration.
            output("interrupted. Is = " + isInterrupted());
        }
    }

    void outputCount() {
        output("" + count++);
    }

    void output(String msg) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < offset; i++) sb.append("    ");
        sb.append(msg);
        log(sb.toString());
    }
}
