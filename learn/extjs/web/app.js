// supposed to improve load times and prevent warning regarding synchronous loads
// it's like an import
Ext.require('Ext.container.Viewport');
Ext.require('Ext.panel.Panel');
Ext.require('Ext.app.Application');

function createApp(pathToRoot, controllers, centerConfig) {
    var result = Ext.create('Ext.app.Application', {
        name: 'LE',
        appFolder: pathToRoot + '/app' ,
        controllers: controllers,
        views: ['LE.view.Menubar'],
        launch: function() {
    // TODO: xtype: 'appmenubar' not working
            // NOTE: Taking this out to a separate statement will break the appointlist xtype (?!)
            Ext.create('Ext.container.Viewport', {
                layout: 'border',
                items:[
                    {
                        region: 'north',
                        items: Ext.create('LE.view.Menubar',{
                            pathToRoot: pathToRoot
                        }),
                        autoHeight: true
                    },
                    Ext.apply(centerConfig, { region: 'center' })
                ]
            });
        }
    });
    console.log("Created app: ", result);
    return result;
}

function ajaxLoader() {
    var arr = Ext.ComponentQuery.query('#ajax-loader');
    return arr[0];
}
