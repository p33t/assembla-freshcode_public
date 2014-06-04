package pkg;

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
