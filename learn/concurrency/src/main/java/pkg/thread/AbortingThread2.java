package pkg.thread;

/**
 * An alternative implementation using the interrupt mechanism.
 */
public class AbortingThread2 extends TestThread {

    private AbortingThread2(int offset) {
        super(offset);
    }

    public static void main(String[] args) {
        final AbortingThread2[] threads = new AbortingThread2[10];
        log("Launching threads");
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new AbortingThread2(i);
            threads[i].start();
        }

        sleepAwhile();
        log("Aborting threads");
        for (AbortingThread2 thread : threads) thread.abort();

        log("Main finished");
    }

    private void abort() {
        interrupt(); // will cause conclusion ASAP.
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            sleepFor(1000);
            outputCount();
        }
        output("done");
    }
}
