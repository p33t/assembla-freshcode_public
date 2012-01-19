var TEST_CONFIG = {
    app: undefined
};

/**
 * Creates the Learn Extjs (LE) application and returns it.  Result is cached in TEST_CONFIG.app var.
 */
function testApp() {
    if (TEST_CONFIG.app === undefined) {
        var launched = false;
        var pathToRoot = calcPathToRoot();
        TEST_CONFIG.app = Ext.create('Ext.app.Application', {
            name: 'LE',
            appFolder: pathToRoot + '/app',
            launch: function() {
                log('App successfully launched.');
            }
        });
        log('Created app', TEST_CONFIG.app);
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
