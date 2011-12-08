// supposed to improve load times and prevent warning regarding synchronous loads
// it's like an import
Ext.require('Ext.container.Viewport');
Ext.require('Ext.panel.Panel');
//This causes an error ?!!! Ext.require('Ext.application.Application');

function createApp(pathToRoot, contentEl) {
//    return Ext.create('Ext.application.Application', {
    Ext.application({
        name: 'LE',
        appFolder: pathToRoot + '/app',
        launch: function() {
            Ext.create('Ext.container.Viewport', {
                layout: 'fit',
                items: [
                    Ext.create('Ext.panel.Panel', {
                        tbar: menutoolbar(pathToRoot),
                        contentEl: contentEl
                    })
                ]
            });
        }
    });
}

function menutoolbar(pathToRoot) {
    var pathPrefix = pathToRoot + '/';
    return Ext.create('Ext.toolbar.Toolbar',
        {
            style: {
                background: 'inherit',
                border: 0
            },
            items:[
                {xtype:'image', src: pathPrefix + 'image/logo-med.jpg', height: 50, width: 80},
                {xtype: 'tbfill'},
                {
                    text:'Experiments',
                    menu:{
                        plain: true,
                        items: [
                            {
                                text: 'Menubar',
                                href: pathPrefix + 'experiment/menubar.html'
                            },
                            {
                                text: 'My Module',
                                href: pathPrefix + 'experiment/mymodule'
                            },
                            {
                                text: 'Whole Page',
                                href: pathPrefix + 'experiment/wholepage'
                            },
                            {
                                text: 'HTML Inside',
                                href: pathPrefix + 'experiment/htmlinside'
                            }
//                                    May be useful later...
//                                    {
//                                        text: 'Nested',
//                                        menu: {
//                                            plain: true,
//                                            items: [
//                                                {
//                                                    text: 'Sub 11',
//                                                    href: 'http://www.google.com/search'
//                                                }
//                                            ]
//                                        }
//                                    }
                        ]
                    }
                },
                {
                    xtype: 'button',
                    text: 'MVC Tutorial',
                    handler: function() {
                        window.location = pathPrefix + 'mvc-tutorial';
                    }
                },
                {
                    xtype: 'button',
                    text: 'Debug',
                    handler: function() {
                        alert('None');
                    }
                },
                {
                    xtype: 'splitbutton',
                    text:'Ajax Loader',
                    handler: function(btn) {
                        var unchecked = btn.menu.query('menucheckitem[checked=false]')[0];
                        // NOTE: A little dodgy because we're invoking other parts of the UI (?)
                        unchecked.setChecked(true);
                    },
                    menu:{
                        plain: true,
                        items: [
                            // stick any markup in a menu
                            '<b class="menu-title">Visible</b>',
                            {
                                text: 'Yes',
                                checked: false,
                                group: 'ajaxLoaderVisible',
                                checkHandler: function(item, checked) {
                                    if (checked) ajaxLoader().show();
                                }
                            }, {
                                text: 'No',
                                checked: true,
                                group: 'ajaxLoaderVisible',
                                checkHandler: function(item, checked) {
                                    if (checked) ajaxLoader().hide();
                                }
                            }
                        ]
                    }
                },
                {xtype: 'tbfill'},
                {
                    xtype: 'container',
                    height: 50,
                    width: 80,
                    items:[
                        {
                            xtype: 'image',
                            id: 'ajax-loader',
                            src: pathPrefix + 'image/ajax-loader.gif',
                            hidden: true
                        }
                    ]
                }
            ]
        }
    );
}

function ajaxLoader() {
    var arr = Ext.ComponentQuery.query('#ajax-loader');
    return arr[0];
}
