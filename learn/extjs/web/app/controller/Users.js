Ext.define('LE.controller.Users', {
    extend: 'Ext.app.Controller',

    init: function() {
        this.control({
            'viewport > panel': { // A ComponentQuery that yields all panels that are on a view port
                render: this.onPanelRendered
            }
        });
    },

    onPanelRendered: function() {
        console.log('The panel was rendered');
    }
});
