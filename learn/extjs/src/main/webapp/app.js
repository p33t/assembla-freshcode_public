// supposed to improve load times and prevent warning regarding synchronous loads
// it's like an import
Ext.require('Ext.container.Viewport');
Ext.require('Ext.app.Application');

function calcPathToRoot() {
    return Ext.Loader.getPath('Ext') + '/../..';
}

function createApp(controllers, centerConfig) {
    var pathToRoot = calcPathToRoot();
    var result = Ext.create('Ext.app.Application', {
        name: 'LE',
        appFolder: pathToRoot + '/app',
        controllers: ['Menubar'].concat(controllers),
        launch: function() {
            // NOTE: Taking this out to a separate statement will break the appointmentlist xtype
            // It appears the 'controllers' field defines preloaded views whose xtypes can be used.
            Ext.create('Ext.container.Viewport', {
                layout: 'border',
                items:[
                    {
                        region: 'north',
                        items: {
                            xtype: 'appmenubar',
                            pathToRoot: pathToRoot
                        },
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
