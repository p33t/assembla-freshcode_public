Ext.define("LE.view.richsettings.Panel", {
    extend: "Ext.container.Container",
    alias: 'widget.richPanel',
//    html: '<p>Ugh</p>',
    config: {
        settings: []
    },
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
//        this.syncDisplay();
        return result;
    },
    applySettings: function(settings) {
        log("Applying settings: ", settings);
        if (!Ext.isArray(settings)) return undefined;
        this.syncDisplay(settings);
        return settings;
    },
    syncDisplay: function(settings) {
//        var settings = this.getSettings();
        log('Syncing with ', settings);
        var html = "";
        for (var ix in settings) {
            html += Html.p(settings[ix])
        }
        if (html === "") html = Html.p(Ext.htmlEncode('<none>'));

        this.removeAll();
        this.add(Ext.create('Ext.Component', {
            html: html
        }));

    }
});
