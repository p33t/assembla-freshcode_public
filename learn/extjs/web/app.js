// supposed to improve load times and prevent warning regarding synchronous loads
// it's like an import
Ext.require('Ext.container.Viewport');

Ext.application({
    name: 'LE', // Learn ExtJS
    appFolder: 'app',

    controllers: [
        'Users'
    ],

    launch: function() {
        Ext.create('Ext.container.Viewport', {
            items: [
                {
                    xtype: 'toolbar',
                    items:[
                        {xtype: 'tbfill'},
                        {
                            xtype: 'button',
                            text: 'Link',
                            handler: function() {
                                window.location = 'http://www.google.com/';
                            }
                        },
//                        Doesn't do the 'hover' thing
//                        {
//                            xtype: 'menuitem',
//                            text: 'Link',
//                            href: 'http://www.google.com/search',
//                            hrefTarget: '_self'
//                        },
                        {xtype: 'tbfill'}
                    ]
                },
                {
                    xtype: 'userlist'
                }
            ]
        });
    }
});
