package pkg.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A thread that can be paused.  Use this technique instead of Thread.suspend(), which causes deadlocks.
 */
public class PausableThread extends TestThread {
    private static AtomicInteger counter = new AtomicInteger();
    private final Object privateLock = new Object();
    private boolean paused;

    private PausableThread() {
        super(counter.incrementAndGet());
        setDaemon(true); // means we don't have to abort the threads explicitly
    }

    public static void main(String[] args) {
        PausableThread t = new PausableThread();
        log("Launching thread");
        t.start();
        sleepFor(4000);
        log("Pausing thread");
        t.pause();
        sleepFor(4000);
        log("Unpausing thread");
        t.unPause();
        sleepFor(2000);
        log("Finished");
    }

    private void unPause() {
        synchronized (privateLock) {
            paused = false;
            privateLock.notifyAll(); // runtime error when not in a synchronised block
        }
    }

    private void pause() {
        synchronized (privateLock) {
            paused = true;
        }
        interrupt(); // to get out of sleep and into paused state
    }

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            synchronized (privateLock) {
                while (paused) {
                    output("waiting");
                    try {
                        privateLock.wait(); // needs to be in synchronised block on same monitor object
                    } catch (InterruptedException e) {
                        // interruped.
                    }
                }
            }
            cycle();
        }
    }


}
