package pkg.thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class SimpleOutputThread extends Thread {
    private final int offset;
    private int count = 1;
    private boolean abort;

    private SimpleOutputThread(int offset) {
        this.offset = offset;
    }

    public static void main(String[] args) {
        final SimpleOutputThread[] threads = new SimpleOutputThread[10];
        log("Launching threads");
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new SimpleOutputThread(i);
            threads[i].start();
        }

        Timer timer = new Timer("Controller");
        log("Starting timer");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log("Aborting threads");
                for (SimpleOutputThread thread : threads) thread.abort();
            }
        }, 10000);
        log("Main finished");
    }

    private static void log(String s) {
        Logger.getGlobal().info(s);
    }

    private void abort() {
        abort = true;
    }

    @Override
    public void run() {
        while (!abort) {
            output("" + count++);
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                // do nothing.  This just shortens the sleep duration.
                output("interrupted");
            }
        }
    }

    private void output(String msg) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < offset; i++) sb.append("    ");
        sb.append(msg);
        log(sb.toString());
    }
}
