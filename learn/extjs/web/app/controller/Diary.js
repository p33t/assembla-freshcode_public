Ext.define('LE.controller.Diary', {
    extend: 'Ext.app.Controller',

    // these will be loaded up front
    views: [ 'appointment.List'],
    stores: ['Diary'],
    models: ['Appointment']
});
