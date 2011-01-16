package biz.freshcode.qjug2011.ui;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static biz.freshcode.qjug2011.util.AppExceptionUtil.illegalState;
import static biz.freshcode.qjug2011.util.AppThreadUtil.daemon;

@Component
@Lazy(true)
public class BackgroundWorker implements DisposableBean {
    private Thread threadOrNull;
    @Inject private TailingPane tailer;
    private long loopCounter = 0;
    
    public void activate() {
        checkRunning(false);
        threadOrNull = daemon(new MyRunnable());
        threadOrNull.start();
    }

    public void deactivate() {
        checkRunning(true);
        // TODO: Wait for it to shut down?
        threadOrNull.interrupt();
        threadOrNull = null;
    }

    @Override
    public void destroy() throws Exception {
        if (isRunning()) deactivate();
    }

    public boolean isRunning() {
        return threadOrNull != null;
    }

    private void checkRunning(boolean expected) {
        if (isRunning() != expected) throw illegalState("Worker is already " + (expected? "Running": "Stopped"));
    }
    
    private void run() {
        while (! (threadOrNull == null || threadOrNull.isInterrupted())) {
            tailer.append("Background loop " + loopCounter++);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // do nothing
            }
        }
    }

    private class MyRunnable implements Runnable{
        @Override
        public void run() {
            BackgroundWorker.this.run();
        }
    }
}
