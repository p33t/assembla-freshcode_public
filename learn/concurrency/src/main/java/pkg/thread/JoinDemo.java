package pkg.thread;

public class JoinDemo {
    public static void main(String[] args) {
        Thread t = new Thread(() -> TestThread.sleepFor(2000));
        TestThread.log("Launching thread.");
        t.start();
        try {
//            t.join(1000); .... this one simply returns after timeout without having waited.
            t.join();
            TestThread.log("Yay, joined!");
            TestThread.log("Alt thread state: " + t.getState());
        } catch (InterruptedException e) {
            TestThread.log("Failed to join");
        }
    }
}
