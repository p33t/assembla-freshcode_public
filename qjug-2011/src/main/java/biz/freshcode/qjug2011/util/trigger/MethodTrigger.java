package biz.freshcode.qjug2011.util.trigger;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static biz.freshcode.qjug2011.util.AppObjectUtils.classOf;
import static biz.freshcode.qjug2011.util.AppReflectUtil.*;

public class MethodTrigger implements InvocationHandler, Runnable {
    private WeakReference<Object> targetRef;
    private Method method;
    private Object[] args;

    public Object[] getOriginalArgs() {
        return args.clone();
    }

    public void init(Object inst, Method method, Object... args) {
        setTarget(inst);
        this.method = method;
        this.args = args;
    }
    
    @Override
    public Object invoke(Object o, Method method, Object[] objects) {
        return trigger(objects);
    }

    @Override
    public void run() {
        trigger();
    }

    public <T> T toCall(T inst, Object... constructorArgs) {
        Class<T> cls = classOf(inst);
        return toCall(inst, cls, constructorArgs);
    }

    public <T> T toCall(final Object inst, Class<T> cls, Object... constructorArgs) {
        InvocationHandler ih = new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) {
                init(inst, method, objects);
                return defVal(method);
            }
        };
        return captureInvocation(cls, ih, constructorArgs);
    }
    
    public <T> T trigger(Object... possibleArgs) {
        if (method == null) throw notInitialised();
        Object target = getTarget();
        Object result;
        if (target == null) result = defVal(method);
        else {
            Object[] useArgs = decideArgs(possibleArgs);
            result = invokeMethod(method, target, useArgs);
        }
        //noinspection unchecked
        return (T) result;
    }

    private Object[] decideArgs(Object[] possibleArgs) {
        Class[] types = method.getParameterTypes();
        Object[] args;
        if (types.length == 0) args = new Object[0];
        else if (matches(method, possibleArgs)) args = possibleArgs;
        else args = this.args;
        return args;        
    }
    
    private Object getTarget() {
        if (targetRef == null) throw notInitialised();
        return targetRef.get();
    }

    private NotInitializedException notInitialised() {
        return new NotInitializedException();
    }

    private void setTarget(Object obj) {
        targetRef = new WeakReference<Object>(obj);
    }

    public static class NotInitializedException extends RuntimeException {
        private NotInitializedException() {
            super("Not yet initialized.");
        }
    }
}
