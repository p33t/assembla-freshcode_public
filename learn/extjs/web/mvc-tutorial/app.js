// supposed to improve load times and prevent warning regarding synchronous loads
// it's like an import
Ext.require('Ext.container.Viewport');

Ext.application({
    name: 'MVC',
    appFolder: 'app',

    controllers: [
        'Users'
    ],

    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            items: [
                {
                    xtype: 'userlist'
                }
// Wrapping in a panel causes the scroll bars to stop working.
// Sencha are aware I think... http://www.sencha.com/forum/showthread.php?142473
//                {
//                    xtype: 'panel',
//                    layout: 'fit',
//                    items: [
//                        {
//                            xtype: 'userlist'
//                        }
//                    ]
//                }
            ]
        });
    }
});
