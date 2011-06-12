package biz.freshcode.swing_shots.util;

import org.testng.annotations.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static biz.freshcode.swing_shots.util.AppArrayUtils.arr;
import static biz.freshcode.swing_shots.util.AppExceptionUtil.illegalState;
import static biz.freshcode.swing_shots.util.AppReflectUtil.captureInvocation;
import static biz.freshcode.swing_shots.util.AppReflectUtil.defVal;
import static biz.freshcode.swing_shots.util.Ref.ref;
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
        assertEquals(r.val.method, MyClass.class.getMethod("pop", String.class));
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
        assertEquals(r.val.method, MyInterface.class.getMethod("bruce", String.class));
        assertEquals(r.val.args, arr("lee"));
    }

    @Test
    public void testDefVal() {
        assertEquals(defVal(Boolean.TYPE), Boolean.FALSE);
        assertEquals(defVal(Byte.TYPE), (byte) 0);
        assertEquals(defVal(Short.TYPE), (short) 0);
        assertEquals(defVal(Integer.TYPE), 0);
        assertEquals(defVal(Long.TYPE), 0L);
        assertEquals(defVal(Float.TYPE), 0f);
        assertEquals(defVal(Double.TYPE), 0.0);
        assertEquals(defVal(Character.TYPE), '\u0000');
    }

    public interface MyInterface {
        void bruce(String surname);

        void noise();
    }

    public static class MyClass {
        private String arg1;
        private int arg2;

        public String getArg1() {
            return arg1;
        }

        public int getArg2() {
            return arg2;
        }

        // NOTE: Primitive argument
        public MyClass(String arg1, int arg2) {
            this.arg1 = arg1;
            this.arg2 = arg2;
            noise();
        }

        // NOTE: Primitive return type
        public int pop(String arg) {
            throw illegalState("Should never reach here");
        }

        public void noise() {
            // do something to prevent optimisation removal
            arg1 += arg2;
        }
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
