Ext.define('LE.view.Menubar', {
    extend: 'Ext.toolbar.Toolbar',
    alias : 'widget.appmenubar',
    config: {
        pathToRoot: 'pathToRootIsRequired'
    },
    initComponent: function() {
        var pathPrefix = this.getPathToRoot() + '/';
        this.style = {
            background: 'inherit',
            border: 0
        };
        this.items = [
            {xtype:'image', src: pathPrefix + 'image/logo-med.jpg', height: 50, width: 80},
            {xtype: 'tbfill'},
            {
                text:'Experiments',
                menu:{
                    plain: true,
                    items: [
                        {
                            text: 'Forms',
                            menu: {
                                plain: true,
                                items: [
                                    {
                                        text: 'Simple',
                                        href: pathPrefix + 'experiment/form/simple'
                                    }
                                ]
                            }
                        },
                        {
                            text: 'Window Grid',
                            href: pathPrefix + 'experiment/windowgrid'
                        },
                        {
                            text: 'Drag \'n Drop',
                            href: pathPrefix + 'experiment/draganddrop'
                        },
                        {
                            text: 'XTemplate',
                            href: pathPrefix + 'experiment/xtemplate'
                        },
                        {
                            text: 'Rich Settings',
                            href: pathPrefix + 'experiment/richsettings'
                        },
                        {
                            text: 'HTML Render',
                            href: pathPrefix + 'experiment/htmlrender'
                        },
                        {
                            text: 'Menubar',
                            href: pathPrefix + 'experiment/menubar.html'
                        },
                        {
                            text: 'Whole Page',
                            href: pathPrefix + 'experiment/wholepage'
                        },
                        {
                            text: 'HTML Inside',
                            href: pathPrefix + 'experiment/htmlinside'
                        },
                        {
                            text: 'Proxy',
                            href: pathPrefix + 'experiment/proxy'
                        },
                        {
                            text: 'Modules',
                            menu: {
                                plain: true,
                                items: [
                                    {
                                        text: 'Good',
                                        href: pathPrefix + 'experiment/mymodule/good.html'
                                    },
                                    {
                                        text: 'Bad',
                                        href: pathPrefix + 'experiment/mymodule/bad.html'
                                    }
                                ]
                            }
                        },
                        {
                            text: 'Layout',
                            menu: {
                                plain: true,
                                items: [
                                    {
                                        text: 'Table',
                                        href: pathPrefix + 'experiment/layout/table'
                                    }
                                ]
                            }
                        }
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
                        Html.elem('b', {class: "menu-title"}, 'Visible'),
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
        ];
        this.callParent(arguments);
    }
});