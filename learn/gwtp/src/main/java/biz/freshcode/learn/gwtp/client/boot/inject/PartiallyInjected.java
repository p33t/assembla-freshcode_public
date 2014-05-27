package biz.freshcode.learn.gwtp.client.boot.inject;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import static biz.freshcode.learn.gwtp.client.util.AppClientUtil.LOG;

public class PartiallyInjected {
    @Inject
    public PartiallyInjected(FullyInjected fully, @Assisted String assist) {
        LOG.info(getClass().getName() + " instantiated with " + fully + " & " + assist);
    }

    public interface Factory {
        // NOTE: @Assist prevents GIN errors during compile.
        PartiallyInjected create(@Assisted String assist);
    }
}
