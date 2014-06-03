package pkg;

import org.apache.catalina.realm.DataSourceRealm;

public class TestRealm extends DataSourceRealm {
    protected static final String name = "TestRealm";

    @Override
    protected String getName() {
        return name;
    }
}
