package pkg;

/**
 * An experiment with JNDI custom resource and BeanFactory.
 *
 * @see org.apache.naming.factory.BeanFactory
 */
@SuppressWarnings("UnusedDeclaration")
public class SomeBean {
    private String str;
    private int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " with str=" + str + ", i=" + i;
    }
}
