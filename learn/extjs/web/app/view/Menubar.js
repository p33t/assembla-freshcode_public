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
        ];
        this.callParent(arguments);
    }
});