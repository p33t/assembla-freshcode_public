package pkg.thread;

/**
 * A thread with an 'abort' method.  Use this instead of Thread.stop() (which causes deadlocks).
 */
public class AbortingThread extends TestThread {
    private boolean abort;

    private AbortingThread(int offset) {
        super(offset);
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

        sleepAwhile();
        log("Aborting threads");
        for (AbortingThread thread : threads) thread.abort();

        log("Main finished");
    }

    private void abort() {
        abort = true;
        interrupt(); // will cause conclusion ASAP.
    }

    @Override
    public void run() {
        while (!abort) cycle();
        output("done");
    }
}
