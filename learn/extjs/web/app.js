// supposed to improve load times and prevent warning regarding synchronous loads
// it's like an import
Ext.require('Ext.container.Viewport');

Ext.application({
    launch: function() {
        Ext.create('Ext.container.Viewport', {
            items: [
                {
                    xtype: 'toolbar',
                    style: {
                        background: 'inherit',
                        border: 0
                    },
                    items:[
                        {xtype:'image', src: 'image/logo-med.jpg', height: 50, width: 80},
                        {xtype: 'tbfill'},
                        {
                            text:'Experiments',
                            menu:{
                                plain: true,
                                items: [
                                    {
                                        text: 'Menubar',
                                        href: 'experiment/menubar.html'
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
                                window.location = 'mvc-tutorial';
                            }
                        },
                        {
                            id: 'ajaxLoader',
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
                                    src: 'image/ajax-loader.gif',
                                    hidden: true
                                }
                            ]
                        }
                    ]
                }
            ]
        });
    }
});

function ajaxLoader() {
    var arr = Ext.ComponentQuery.query('#ajax-loader');
    return arr[0];
}
