package pkg;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

public class AppUtil {
    public static final List<String> ROLES = unmodifiableList(asList(
            "Everyone",
            "BUILTIN\\Users",
            "BUILTIN\\Administrators",
            "BUILTIN\\Remote Desktop Users",
            "CONSOLE LOGON"
    ));
}
