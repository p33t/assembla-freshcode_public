package biz.freshcode.learn.gwtp.client.bug.hashcodedirty;

import biz.freshcode.learn.gwtp.client.test.TestUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.junit.client.GWTTestCase;
import com.sencha.gxt.widget.core.client.form.TextField;

import static biz.freshcode.learn.gwtp.shared.util.AppCollectionUtil.newSetFrom;

/**
 * Testing for influence of hash code on Driver.isDirty.
 */
public class HashCodeDirtyGwtTest extends GWTTestCase {

    public void testSimple() {
        SimpleDriver driver = GWT.create(SimpleDriver.class);
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

    public void testParent() {
        ParentDriver driver = GWT.create(ParentDriver.class);
        ParentEdit editor = new ParentEdit();
        driver.initialize(editor);

        Parent p = new Parent();
        p.setAlts(newSetFrom(new AltHash("original")));
        driver.edit(p);
        assertFalse(driver.isDirty());

        editor.alts.setValue(newSetFrom(new AltHash("modified")));
        driver.flush();
        assertTrue(driver.isDirty());
    }

    @Override
    public String getModuleName() {
        return TestUtil.MODULE_NAME;
    }

    public interface SimpleDriver extends SimpleBeanEditorDriver<AltHash, AltHashEdit> {
    }

    public interface ParentDriver extends SimpleBeanEditorDriver<Parent, ParentEdit> {
    }

}
