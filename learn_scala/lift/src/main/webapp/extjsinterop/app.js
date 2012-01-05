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

function calcPathToRoot() {
    return Ext.Loader.getPath('Ext') + '/../..';
}

var PATH_TO_ROOT = calcPathToRoot();

var EI = Ext.create('Ext.app.Application', {
    name: 'EI', // ExtJS Interop
    appFolder: PATH_TO_ROOT + '/app',
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
