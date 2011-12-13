Ext.define('LE.store.Diary', {
    extend: 'Ext.data.Store',
    model: 'LE.model.Appointment',
    data: {
        appointments: [
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'},
            {description: 'Default Appointment', start: '2011-12-07', duration: '30'}
        ]
    },
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'appointments'
        }
    }});
