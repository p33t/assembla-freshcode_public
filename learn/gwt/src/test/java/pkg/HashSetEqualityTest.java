package pkg;

import junit.framework.TestCase;

import java.util.Set;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newSetFrom;

/**
 * Trying to figure out is HashSet has implemented hashCode() and equals() properly.
 */
public class HashSetEqualityTest extends TestCase {
    public void test() {
        AltHash a1 = altHash("s1", 99);
        AltHash a1b = altHash("s1", 99);
        AltHash a2 = altHash("s2", 99);
        assertFalse(a1.equals(a2));
        assertEquals(a1, a1b);

        Set<AltHash> s1 = newSetFrom(a1);
        Set<AltHash> s1b = newSetFrom(a1);
        assertEquals(s1, s1b);

        Set<AltHash> s2 = newSetFrom(a2);
        assertFalse(s2.equals(s1));

        // this is the critical differentiator.... only hash code is used from elements.
        assertEquals(s1.hashCode(), s2.hashCode());
    }

    private AltHash altHash(String str, int integer) {
        AltHash a1 = new AltHash();
        a1.setStr(str);
        a1.setInteger(integer);
        return a1;
    }

    public static class AltHash {
        private String str;
        private int integer;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AltHash altHash = (AltHash) o;

            if (integer != altHash.integer) return false;
            //noinspection RedundantIfStatement
            if (str != null ? !str.equals(altHash.str) : altHash.str != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            // NOTE: not all fields used.
            return integer;
        }

        public int getInteger() {
            return integer;
        }

        public void setInteger(int integer) {
            this.integer = integer;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }
}
