// supposed to improve load times and prevent warning regarding synchronous loads
// it's like an import
Ext.require('Ext.container.Viewport');

function calcPathToRoot() {
    return Ext.Loader.getPath('Ext') + '/../..';
}

var EI = Ext.create('Ext.app.Application', {
    name: 'EI', // ExtJS Interop
    appFolder: calcPathToRoot() + '/app',
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
