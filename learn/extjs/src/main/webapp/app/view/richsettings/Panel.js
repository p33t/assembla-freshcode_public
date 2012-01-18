Ext.define("LE.view.richsettings.Panel", {
    extend: "Ext.container.Container",
    alias: 'widget.richPanel',
    // put field directly among defaults and hope it does not collide
    paragraphs: ['&lt;none&gt;'],

    // the less component-y, more object-y way
    config: {
        settings: []
    },

    // NOTE: No compatibility of constructor with 'config' fields
    // Also, we can't just plonk fields in the base of the class in case they collide with
    // parent configuration.
    useless____constructor: function(settings) {
        log("constructor with config", settings);
//        this.config.settings = settings;
        this.setSettings = settings;

        // NOTE: No args conveyed.
        return this.callParent();
    }
    ,
    initComponent: function() {
        log('initComponent');
        var result = this.callParent(arguments);
        log('initComponent callParent result:', result);
        this.syncDisplay(this.paragraphs);
        return result;
    },
    getParagraphs: function() {
        return this.paragraphs;
    },
    setParagraphs: function(paras) {
        if (!Ext.isArray(paras)) {
            log('ERROR: Rejected paragraph change because it is not an array:"',
                paras,
                '".  Ext.getDisplayName(arguments.callee):',
                Ext.getDisplayName(arguments.callee));
        }
        this.paragraphs = paras;
        this.syncDisplay(paras);
    },
    // This was the .config{} way.
//    applySettings: function(settings) {
//        log("Applying settings: ", settings);
//        if (!Ext.isArray(settings)) return undefined;
////        This is ugly.  Need to cascade the change but it is not committed yet.
//        this.syncDisplay(settings);
//        return settings;
//    },
    syncDisplay: function() {
//        var settings = this.getSettings();
        var paras = this.paragraphs;
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
