// supposed to improve load times and prevent warning regarding synchronous loads
// it's like an import
Ext.require('Ext.container.Viewport');
Ext.require('Ext.app.Application');

/**
 * Substitute for console.log that doesn't barf in certain browsers
 */
function log() {
    if (typeof console == 'undefined') return;
    console.log.apply(console, arguments);
}

function calcPathToRoot() {
    return Ext.Loader.getPath('Ext') + '/../..';
}

var CONFIG = {
  controllers: ['Menubar'],
  centerConfig: {html: '<p>Default contents</p>'}
};

function createApp() {
    var pathToRoot = calcPathToRoot();
    var result = Ext.create('Ext.app.Application', {
        name: 'LE',
        appFolder: pathToRoot + '/app',
        controllers: CONFIG.controllers,
        launch: function() {
            // NOTE: Taking this out to a separate statement will break the appointmentlist xtype
            // It appears the 'controllers' field defines preloaded views whose xtypes can be used.
            Ext.create('Ext.container.Viewport', {
                layout: 'border',
                items:[
                    {
                        region: 'north',
                        items: {
                            xtype: 'appmenubar',
                            pathToRoot: pathToRoot
                        },
                        autoHeight: true
                    },
                    Ext.apply(CONFIG.centerConfig, { region: 'center' })
                ]
            });
        }
    });
    console.log("Created app: ", result);
    return result;
}

function ajaxLoader() {
    var arr = Ext.ComponentQuery.query('#ajax-loader');
    return arr[0];
}

var Html = {
    attrib: function(name, value) {
        return name + '="' + value + '"';
    },
    attribs: function(obj) {
        var accum = "";
        for (var prop in obj) {
            if (accum !== "") accum += " ";
            accum = accum + this.attrib(prop, obj[prop])
        }
        return accum;
    },
    /**
     * Render a string element.
     * @param name The name of the element.
     * @param attrs Optional json object describing the desired attributes.
     * @param content Optional string which will be the content.
     */
    elem: function(name, attrs, content) {
        if (Ext.isDefined(attrs) && !Ext.isDefined(content)) {
            // only one optional arg supplied
            if (!Ext.isObject(attrs)) {
                // need to shuffle
                content = attrs;
                attrs = undefined;
            }
        }


        var attrString = "";
        if (Ext.isDefined(attrs)) attrString = " " + this.attribs(attrs);
        var lead = '<' + name + attrString;
        if (Ext.isDefined(content)) return lead + '>' + content + '</' + name + '>';
        else return lead + "/>";
    }
};
