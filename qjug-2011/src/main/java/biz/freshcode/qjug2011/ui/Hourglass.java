package biz.freshcode.qjug2011.ui;

import biz.freshcode.qjug2011.util.Ref;
import biz.freshcode.qjug2011.util.trigger.MethodTrigger;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.swing.*;
import java.lang.reflect.Method;
import java.util.List;

import static biz.freshcode.qjug2011.ui.Blocker.blockerFor;
import static biz.freshcode.qjug2011.util.AppReflectUtil.invokeMethod;
import static biz.freshcode.qjug2011.util.Ref.ref;
import static biz.freshcode.qjug2011.util.trigger.UseTrigger.*;

@Component
public class Hourglass {
    @Inject private FrameRegistry frameReg;

    public <T> T surround(T inst, Object... constructorArgs) {
        // TODO: Thread and serial access checks.
        Ref<T> r = ref();
        MethodTrigger t = restrictedCapture(inst, r, Void.TYPE, constructorArgs);
        t.toCall(this).surround(inst, SUPPLIED_METHOD, SUPPLIED_ARGS);
        return r.val;
    }

    // TODO: Use swing workers.
    <T> void surround(T inst, Method m, Object[] args) {
        List<JFrame> frames = frameReg.listFrames();
        for (JFrame frame : frames) {
            blockerFor(frame).block();
        }
        try {
            invokeMethod(m, inst, args);
        } finally {
            for (JFrame frame : frames) {
                blockerFor(frame).unblock();
            }
        }
    }
}
