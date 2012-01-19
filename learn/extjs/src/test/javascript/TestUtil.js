var TEST_CONFIG = {
    app: undefined
};

/**
 * Creates the Learn Extjs (LE) application and returns it.
 */
function testApp() {
    if (TEST_CONFIG.app === undefined) {
        var pathToRoot = calcPathToRoot();
        TEST_CONFIG.app = Ext.create('Ext.app.Application', {
            name: 'LE',
            appFolder: pathToRoot + '/app'
        });
    }
    return TEST_CONFIG.app;
}

/**
 * Returns 'true' if the current spec has experienced failures.
 */
function specFailed() {
    var s = jasmine.getEnv().currentSpec.results();
//    log("currentSpec: ", s);
    return s.failedCount > 0;
}

/**
 * Throws an error if the current spec has failed.
 * Call this if the failure of the spec requires the abortion of the test, otherwise Jasmine just keeps on truckin'.
 */
function specCheck() {
    if (specFailed()) {
//        log("throwing spec error");
        throw new Error("Spec has failed.");
    }
}
