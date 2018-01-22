package pkg.thread;

import java.util.logging.Logger;

public class AbortingThread extends Thread {
    private final int offset;
    private int count = 1;
    private boolean abort;

    private AbortingThread(int offset) {
        this.offset = offset;
    }

    public static void main(String[] args) {
        final AbortingThread[] threads = new AbortingThread[10];
        log("Launching threads");
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new AbortingThread(i);
            threads[i].start();
        }

        // NOTE: Daemon thread 'false' means process never ends.
        // Alternatively, timer.cancel() will conclude the timer's background thread.
        /*There must be a better way...  Timer timer = new Timer("Controller", true);
        log("Starting timer");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log("Aborting threads");
                for (SimpleOutputThread thread : threads) thread.abort();
            }
        }, 10000);*/

        try {
            sleep(10000);
        } catch (InterruptedException e) {
            // interrupted early
        }
        log("Aborting threads");
        for (AbortingThread thread : threads) thread.abort();

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
        output("done");
    }

    private void output(String msg) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < offset; i++) sb.append("    ");
        sb.append(msg);
        log(sb.toString());
    }
}
