/**
 * Creates the Learn Extjs (LE) application and returns it.
 */
function testApp() {
    return Ext.create('Ext.app.Application', {
        name: 'EI',
        appFolder: CONFIG.rootPath.js + '/app'
    });
}

// The webapp needs to be set up on another port
CONFIG.rootPath.ajax = 'http://localhost:8180/scala-lift';
log("CONFIG.rootPath.ajax changed to", CONFIG.rootPath.ajax);

var TEST_CONFIG = {
  ajaxTimeout: 200
};

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
