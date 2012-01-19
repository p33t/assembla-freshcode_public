/**
 * NOTE: This was written before learning more advanced features of views.
 * These advanced features are illustrated in examples/dd/dragdropzones.js.
 */
Ext.define("LE.view.richsettings.Panel", {
    extend: "Ext.panel.Panel",
    alias: 'widget.richPanel',
    border: 1,
    config: {
        paragraphs: ['default-paragraphs']
    },

    constructor: function(config) {
        log("constructor with config", config);
        log("this.config", this.config);
//        if (!this.validate(config.paragraphs)) {
//            restore defaults (?)
//            config.paragraphs = this.config.paragraphs;
//        }
        return this.callParent(arguments);
    },
    initComponent: function() {
        log('initComponent');
        var result = this.callParent(arguments);
        log('initComponent callParent result:', result);
        if (!this.validate(this.getParagraphs())) this.setParagraphs(this.config.paragraphs);
        this.syncDisplay(this.getParagraphs());
        return result;
    },
    // No good...Uncaught TypeError: Cannot call method 'apply' of undefined
//    setParagraphs: function(paras) {
//        this.callParent(arguments);
//        this.syncDisplay();
//    },
    applyParagraphs: function(paras) {
        if (!this.validate(paras)) return undefined;
        // This is nasty...would be nice to respond to notification of change to a property.
        this.syncDisplay(paras);
        return paras;
    },
    validate: function(paras) {
        if (!Ext.isArray(paras)) {
            log('ERROR: Rejected paragraph change because it is not an array:"',
                paras,
                '".  Ext.getDisplayName(arguments.callee):',
                Ext.getDisplayName(arguments.callee));
            return false;
        }
        return true;
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
