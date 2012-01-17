Ext.define("LE.view.richsettings.Panel", {
    extend: "Ext.Component",
    config: {
        settings: []
    },
    constructor: function(settings) {
        log("constructor with config", settings);
        this.setSettings(settings);

        var html = "";
        for (var ix in settings) {
            html += Html.p(settings[ix])
        }
        if (html === "") html = Html.p(Ext.htmlEncode('<none>'));

        this.html = html;

        // NOTE: No args conveyed.
        return this.callParent();
    }
    ,
    initComponent:function() {
        log('initComponent');
        return this.callParent(arguments);
    }
});