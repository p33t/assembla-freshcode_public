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
        // NOTE: In the past, @Assist prevented GIN errors during clean + gwt:run-codeserver
        //       But now it seems to work ?!
        PartiallyInjected create(String assist);
    }
}
