// supposed to improve load times and prevent warning regarding synchronous loads
// it's like an import
Ext.require('Ext.container.Viewport');

var EI = Ext.create('Ext.app.Application', {
    name: 'EI', // ExtJS Interop
    appFolder: 'app',
    controllers: [
        'Users'
    ]
});

function userList(renderTo) {
    // Argh!!! ViewPort.renderTo is ignored!!!!
    Ext.create('Ext.container.Container', {
        renderTo: renderTo,
        items: [
            EI.getView('user.List').create() // EI.view.user.List also works
        ]
    });
}
