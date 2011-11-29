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
                // not really cutting it
                {
                    layout: 'hbox',
                    items: [
                        {
                            xtype: 'menu',
                            text: 'Menu 1',
                            floating: false,
                            plain: true,
                            items: [
                                {
                                    xtype: 'menuitem',
                                    text:'Sub 11',
                                    handler: function() {window.location = 'http://www.google.com/';}
                                }
                            ]
                        },
                        {
                            xtype: 'menu',
                            text: 'Menu 2',
                            floating: false,
                            plain: true,
                            items: [
                                {
                                    xtype: 'menuitem',
                                    text: 'Sub 21',
                                    href: 'http://www.google.com/search',
                                    hrefTarget: '_self'
                                }
//                                Causes vertical stacking
//                                ,
//                                {
//                                    xtype: 'menuitem',
//                                    text: 'Sub 22',
//                                    href: 'http://www.google.com/search',
//                                    hrefTarget: '_self'
//                                }
                            ]
                        },
                        {
                            xtype: 'menu',
                            text: 'Menu 3',
                            floating: false,
                            plain: true,
                            items: [
                                {
                                    xtype: 'menuitem',
                                    text: 'Menu 31',
                                    plain: true,
                                    menu: {items:[
                                        {
                                            xtype: 'menuitem',
                                            text: 'Sub 311',
                                            href: 'http://www.google.com/search',
                                            hrefTarget: '_self'
                                        },
                                        {
                                            xtype: 'menuitem',
                                            text: 'Sub 312',
                                            href: 'http://www.google.com/search',
                                            hrefTarget: '_self'
                                        }
                                    ]}
                                }
                            ]
                        }
                    ]
                },
                {
                    // stacks vertically
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
                            // getting somewhere
                            text:'Button w/ Menu',
                            menu:{
                                plain: true,
                                items: [
                                    {
                                        text: 'Sub 1',
                                        href: 'http://www.google.com/search',
                                        hrefTarget: '_self'
                                    }
                                ]
                            }
                        },
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
