Ext.define('Multi', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.chtMulti',
    layout: 'fit',
    config: {
        data: []
    },
    constructor: function(config) {
        this.callParent(arguments);
        this.initConfig(config);
    },
    initComponent: function() {
        this.callParent(arguments);
        this.add(Ext.widget('panel', {
            html: Html.p('Lee')
        }));
    }
});