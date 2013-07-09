package biz.freshcode.learn.gwtp.client.bug.hashcodedirty;

public class AltHash {
    private String str;

    public AltHash() {
    }

    public AltHash(String str) {
        this.str = str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AltHash altHash = (AltHash) o;

        //noinspection RedundantIfStatement
        if (str != null ? !str.equals(altHash.str) : altHash.str != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return 42; // constant hash
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
