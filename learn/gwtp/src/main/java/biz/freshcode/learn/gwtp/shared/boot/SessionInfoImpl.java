package biz.freshcode.learn.gwtp.shared.boot;

public class SessionInfoImpl implements SessionInfo {
    private long bootTime;

    
    @SuppressWarnings("UnusedDeclaration")
    public SessionInfoImpl() {
    }

    public SessionInfoImpl(long bootTime) {
        this.bootTime = bootTime;
    }

    @Override
    public long getBootTime() {
        return bootTime;
    }
}
