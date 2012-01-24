Ext.define('LE.view.windowform.Window', {
    extend: 'Ext.window.Window',
    alias: 'widget.wfWindow',
    closeAction: 'hide',
    title: 'Hello',
    height: 200,
    width: 400,
    layout: 'fit',
    tbar: [
        '->',
        {text: 'All'},
        {text: 'None'},
        '-',
        {iconCls: 'col-move-bottom'},
        {iconCls: 'col-move-top'}
    ],
    items: {  // Let's put an empty grid in just to illustrate fit layout
        xtype: 'grid',
        border: false,
        columns: [
            {header: 'World'}
        ],                 // One header just for show. There's no data,
        store: Ext.create('Ext.data.ArrayStore', {}) // A dummy empty data store
    }
});