package biz.freshcode.swing_shots.data;

import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.testng.annotations.Test;

import java.util.List;

import static biz.freshcode.swing_shots.test.TestBootstrap.factory;
import static biz.freshcode.swing_shots.util.AppCollectionUtils.newListFrom;
import static junit.framework.Assert.*;

public class H2DatabaseTest {
    H2Database h2 = factory().h2Database("testDb");

    @Test
    public void testOpenClose() {
        h2.openAndResetIfNecessary();
        assertTrue(h2.isOpen());
        h2.close();
        assertFalse(h2.isOpen());
        h2.open();
        assertTrue(h2.isOpen());
        h2.close();
        assertFalse(h2.isOpen());
    }

    @Test
    public void testOps() {
        h2.openAndResetIfNecessary();
        h2.execute("create table t1(id int, name varchar(32));");
        h2.update("insert into t1 values (?, ?);", 1, "one");
        List<String> l = h2.query("select name from t1 where id = ?", new SingleColumnRowMapper<String>(), 1);
        assertEquals(l, newListFrom("one"));
    }
}
