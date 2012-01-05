/**
 * Creates the Learn Extjs (LE) application and returns it.
 */
function testApp() {
    var pathToRoot = calcPathToRoot();
    return Ext.create('Ext.app.Application', {
        name: 'EI',
        appFolder: pathToRoot + '/app'
    });
}
