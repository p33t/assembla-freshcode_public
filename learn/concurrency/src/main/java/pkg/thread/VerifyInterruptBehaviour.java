package pkg.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Exploration of what happens to locks during an 'interrupt'.
 * InterruptedException cannot be processed until required locks are obtained (if in sync block)
 * Remember that each monitor has a blocked group and a waiting group.  Emerging from the waiting group will land a thread in the blocked group.
 */
public class VerifyInterruptBehaviour {
    public static void main(String[] args) {
//        ExecutorService es = Executors.newSingleThreadExecutor();
        SynchronizedTask task1 = new SynchronizedTask();
        Thread thread = new Thread(task1);
        final Future<String> future1 = task1.future;
//        final Future<String> future1 = es.submit(task1);
        thread.start();
        Supplier<String> futureStatus = () -> {
            if (future1.isDone()) {
                if (future1.isCancelled()) return "Cancelled";
                try {
                    return "Done: " + future1.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException("Impossible", e);
                }
            }
            return "Not Done";
        };

        System.out.println("Main ensuring task can acquire lock by sleeping.  Future: " + futureStatus.get());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Benign sleep interrupted ?!");
            return;
        }

        synchronized (task1.monitor) {
            System.out.println("Main acquired lock on " + task1.monitor);

            System.out.println("Main about to interrupt task.  Future: " + futureStatus.get());
            thread.interrupt();

            System.out.println("Main about to sleep.  Future: " + futureStatus.get());
            try {
                Thread.sleep(2000);
                System.out.println("Finished sleeping.  Future: " + futureStatus.get());
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted");
            }

            System.out.println("Main about to exit sync block.  Future: " + futureStatus.get());
        }
//        es.shutdown();
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("Main was interrupted while trying to join");
        }
        System.out.println("Ultimate done: " + futureStatus.get());
    }

    static class SynchronizedTask implements Runnable {
        final String monitor = "SynchronizedTask-" + this.hashCode();
        final CompletableFuture<String> future = new CompletableFuture<>();

        @Override
        public void run() {
            String[] result = {"starting"};

            Consumer<String> indicate = (msg) -> {
                result[0] = msg;
                System.out.println(msg);
            };

            synchronized (monitor) {
                indicate.accept("SynchronisedTask acquired lock " + monitor);
                try {
                    indicate.accept("SynchronisedTask about to wait for lock " + monitor);
                    monitor.wait(1000); // <<<<<< Like automatically getting a notify.  Will go back to 'blocked' as it acquires lock
//                    monitor.wait(); //<<<<<<< Will hang thread if nothing notifies
                    indicate.accept("SynchronisedTask continuing in block synchronized on " + monitor);
                } catch (InterruptedException e) {
                    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! It appears interrupt exception cannot be processed until lock re-acquired
                    boolean holds = Thread.holdsLock(monitor);
                    System.out.format("Interrupted.  Lock re-acquired: %s%n", holds);
                }
                indicate.accept("SynchronisedTask about to relinguish lock.  Lock hold status: " + Thread.holdsLock(monitor));
            }
            indicate.accept("SynchronisedTask about to conclude");
//            return result[0];
            future.complete(result[0]);
        }
    }
}
