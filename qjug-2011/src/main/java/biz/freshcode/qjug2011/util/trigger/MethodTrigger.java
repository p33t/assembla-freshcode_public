package biz.freshcode.qjug2011.util.trigger;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static biz.freshcode.qjug2011.util.AppReflectUtil.*;

public class MethodTrigger implements InvocationHandler {
    private WeakReference<Object> targetRef;
    private Method method;
    private Object[] args;

    @Override
    public Object invoke(Object o, Method method, Object[] objects) {
        return trigger(objects);
    }

    public <T> T toCall(Object inst, Class<T> cls, Object... constructorArgs) {
        setTarget(inst);
        InvocationHandler ih = new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) {
                MethodTrigger.this.method = method;
                args = objects;
                return defVal(method);
            }
        };
        return captureInvocation(cls, ih, constructorArgs);
    }

    public <T> T toCall(T inst, Object... constructorArgs) {
        @SuppressWarnings({"unchecked"})
        Class<T> cls = (Class<T>) inst.getClass();
        return toCall(inst, cls, constructorArgs);
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
