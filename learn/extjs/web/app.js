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
                        {xtype: 'tbfill'}
                    ]
                }
            ]
        });
    }
});
