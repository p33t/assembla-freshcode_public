package biz.freshcode.learn.gwt.client.bug;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.junit.client.GWTTestCase;
import com.sencha.gxt.widget.core.client.form.TextField;

// Turns out the problem (elsewhere) was from 'mutable' data being modified.  Defensive copying was needed.
public class HashCodeDirtyGwtTest extends GWTTestCase {

    public void test() {
        Driver driver = GWT.create(Driver.class);
        AltHashEdit editor = new AltHashEdit();
        driver.initialize(editor);
        driver.edit(new AltHash());
        assertFalse(driver.isDirty());

        editor.str.setValue("Bruce");
        driver.flush();
        assertTrue(driver.isDirty());

        editor.str.setValue(null);
        driver.flush();
        assertFalse(driver.isDirty());
    }

    @Override
    public String getModuleName() {
        return "Mod1JUnit";
    }

    public interface Driver extends SimpleBeanEditorDriver<AltHash, AltHashEdit> {
    }

    public static class AltHashEdit implements Editor<AltHash> {
        public TextField str = new TextField();
    }

    public static class AltHash {
        private String str;

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
}
