// supposed to improve load times and prevent warning regarding synchronous loads
// it's like an import
Ext.require('Ext.container.Viewport');

/**
 * Substitute for console.log that doesn't barf in certain browsers
 */
function log() {
    if (typeof console == 'undefined') return;
    console.log.apply(console, arguments);
}

var CONFIG = function() {
    var defPath = Ext.Loader.getPath('Ext') + '/../..';
    return {
        rootPath: {
            /**
             * The path to the root of the application for javascript file purposes such as ExtJS namespaces.
             * This depends whether the javascript is being run in Jasine test harness.
             */
            js: defPath,
            /**
             * The path to the root of the application for ajax and online service purposes like ExtJS CRUD-like Stores.
             * This may be overridden during Jasmine tests to connect to external services.
             */
            ajax: defPath
        }
    }
}();

var EI = Ext.create('Ext.app.Application', {
    name: 'EI', // ExtJS Interop
    appFolder: CONFIG.rootPath.js + '/app',
    controllers: [
        'Users'
    ]
});

function userList(renderTo) {
    // Argh!!! ViewPort.renderTo is ignored!!!!
    Ext.create('Ext.container.Container', {
        renderTo: renderTo,
        // Doesn't help...layout: 'fit',
        items: [
            EI.getView('user.List').create(), // EI.view.user.List also works
            {
                html: '<p>Bonus Content!... Note that this does not resize.  ' +
                    'A viewport (taking up entire screen) is necessary for that... at least at time or writing.</p>'
            }
        ]
    });
}
