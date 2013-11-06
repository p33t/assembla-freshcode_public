package biz.freshcode.learn.gwtp.shared.boot;

public class SessionInfoImpl implements SessionInfo {
    private long bootTime;
    private Extensions extensions;

    
    @SuppressWarnings("UnusedDeclaration")
    public SessionInfoImpl() {
    }

    public SessionInfoImpl(long bootTime, Extensions extensions) {
        this.bootTime = bootTime;
        this.extensions = extensions;
    }

    @Override
    public long getBootTime() {
        return bootTime;
    }

    public Extensions getExtensions() {
        return extensions;
    }
}
