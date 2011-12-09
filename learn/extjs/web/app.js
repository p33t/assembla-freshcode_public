// supposed to improve load times and prevent warning regarding synchronous loads
// it's like an import
Ext.require('Ext.container.Viewport');
Ext.require('Ext.panel.Panel');
Ext.require('Ext.app.Application');

function createApp(pathToRoot, controllers, centerConfig) {
    var result = Ext.create('Ext.app.Application', {
        name: 'LE',
        appFolder: pathToRoot + '/app' ,
        controllers: controllers
//        ,
//        views: ['LE.view.Menubar']
    });
    // TODO: mymodule/good not working.  xtype not recognised.
    console.log("Created app: ", result);
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

    return result;
}

function ajaxLoader() {
    var arr = Ext.ComponentQuery.query('#ajax-loader');
    return arr[0];
}
