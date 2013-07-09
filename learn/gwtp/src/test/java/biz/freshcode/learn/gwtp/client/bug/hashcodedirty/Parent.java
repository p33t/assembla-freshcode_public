package biz.freshcode.learn.gwtp.client.bug.hashcodedirty;

import java.util.Set;

public class Parent {
    private Set<AltHash> alts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parent parent = (Parent) o;

        if (alts != null ? !alts.equals(parent.alts) : parent.alts != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return alts != null ? alts.hashCode() : 0;
    }

    public Set<AltHash> getAlts() {
        return alts;
    }

    public void setAlts(Set<AltHash> alts) {
        this.alts = alts;
    }
}
