package biz.freshcode.qjug2011.util;

import org.testng.annotations.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static biz.freshcode.qjug2011.util.AppArrayUtils.arr;
import static biz.freshcode.qjug2011.util.AppExceptionUtil.illegalState;
import static biz.freshcode.qjug2011.util.AppReflectUtil.captureInvocation;
import static biz.freshcode.qjug2011.util.AppReflectUtil.defVal;
import static biz.freshcode.qjug2011.util.Ref.ref;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class AppReflectUtilTest {
    @Test
    public void testCaptureInvocationClass() throws Exception {
        final Ref<Invocation> r = ref();
        InvocationHandler h = new RefInvocationHandler(r);
        MyClass myClass = captureInvocation(MyClass.class, h);
        myClass.pop("bruce");
        myClass.noise();
        assertNotNull(r.val);
        assertEquals(r.val.method, MyClass.class.getMethod("pop", arr(String.class)));
        assertEquals(r.val.args, arr("bruce"));
    }
    
    @Test
    public void testCaptureInvocationInterface() throws Exception {
        final Ref<Invocation> r = ref();
        InvocationHandler h = new RefInvocationHandler(r);
        MyInterface myInterface = captureInvocation(MyInterface.class, h);
        myInterface.bruce("lee");
        myInterface.noise();
        assertNotNull(r.val);
        assertEquals(r.val.method, MyInterface.class.getMethod("bruce", arr(String.class)));
        assertEquals(r.val.args, arr("lee"));
    }

    public interface MyInterface {
        void bruce(String surname);
        void noise();
    }

    public static class MyClass {
        // TODO: Constructor args
        public void pop(String arg) {
            throw illegalState("Should never reach here");
        }

        public void noise() {
            // do nothing
        }

        // TODO: Primitive return type
    }

    private static class RefInvocationHandler implements InvocationHandler {
        private final Ref<Invocation> r;

        public RefInvocationHandler(Ref<Invocation> r) {
            this.r = r;
        }

        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            r.val = new Invocation(method, objects);
            return defVal(method);
        }
    }
}
