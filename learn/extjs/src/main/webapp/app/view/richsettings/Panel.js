Ext.define("LE.view.richsettings.Panel", {
    extend: "Ext.container.Container",
    alias: 'widget.richPanel',

    config: {
        paragraphs: []
    },

    constructor: function(config) {
        log("constructor with config", config);
//        TODO: Validate?
        return this.callParent(arguments);
    },
    initComponent: function() {
        log('initComponent');
        var result = this.callParent(arguments);
        log('initComponent callParent result:', result);
        this.syncDisplay(this.getParagraphs());
        return result;
    },
    // No good...Uncaught TypeError: Cannot call method 'apply' of undefined
//    setParagraphs: function(paras) {
//        this.callParent(arguments);
//        this.syncDisplay();
//    },
    applyParagraphs: function(paras) {
        if (!Ext.isArray(paras)) {
            log('ERROR: Rejected paragraph change because it is not an array:"',
                paras,
                '".  Ext.getDisplayName(arguments.callee):',
                Ext.getDisplayName(arguments.callee));
            return undefined;
        }
        // This is nasty...would be nice to respond to notification of change to a property.
        this.syncDisplay(paras);
        return paras;
    },
    syncDisplay: function(paras) {
//        var paras = this.getParagraphs();
        log('Syncing with ', paras);
        var html = "";
        for (var ix in paras) {
            html += Html.p(paras[ix])
        }
        if (html === "") html = Html.p(Ext.htmlEncode('<none>'));

        this.removeAll();
        this.add(Ext.create('Ext.Component', {
            html: html
        }));

    }
});
