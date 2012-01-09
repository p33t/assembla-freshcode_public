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
 */
function specCheck() {
    if (specFailed()) {
//        log("throwing spec error");
        throw new Error("Spec has failed.");
    }
}
