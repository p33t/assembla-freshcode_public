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
                    xtype: 'menu',
                    floating: false,
                    plain: true,
                    items: [
                        {
                            xtype: 'menuitem',
                            text: 'Sub 1',
                            href: 'http://www.google.com/search',
                            hrefTarget: '_self'
                        },
                        {
                            xtype: 'menuitem',
                            text: 'Sub 2',
                            href: 'http://www.google.com/search',
                            hrefTarget: '_self'
                        }
                    ]
                },
                {
                    xtype: 'toolbar',
                    items:[
                        {xtype: 'tbfill'},
                        {
                            xtype: 'button',
                            text: 'Button',
                            handler: function() {
                                window.location = 'http://www.google.com/';
                            }
                        },
//                        Doesn't do the 'hover' thing
                        {
                            xtype: 'menuitem',
                            text: 'Menu Item',
                            href: 'http://www.google.com/search',
                            hrefTarget: '_self'
                        },
                        {
                            xtype: 'menu',
                            floating: false,
                            text: 'Menu',
                            plain: true,
                            items: [
                                {
                                    xtype: 'menuitem',
                                    text:'Sub Item 1',
                                    handler: function() {window.location = 'http://www.google.com/';}
                                }
                            ]
                        },
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
