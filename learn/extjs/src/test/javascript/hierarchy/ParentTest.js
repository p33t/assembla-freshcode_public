describe('Parent model', function() {
    it('boots', function() {
        Hierarchy.boot();
        // NOTE: There might be a simpler explanation to the error messages.
        // Try putting them in adjacent files like /experiment/form/customfield
        log('Completely and utterly owned by "hasMany"');
    });
    xit('models are registered', function() {
        var cm = Ext.ModelManager.get('Child');
        expect(cm).toBeDefined();
    });
    xit('hasMany sample code works', function() {
        Ext.define('Product', {
            extend: 'Ext.data.Model',
            fields: [
                {name: 'id',      type: 'int'},
                {name: 'user_id', type: 'int'},
                {name: 'name',    type: 'string'}
            ]
//            belongsTo: 'User'
        });

        Ext.define('User', {
            extend: 'Ext.data.Model',
            fields: [
                {name: 'id',   type: 'int'},
                {name: 'name', type: 'string'}
            ],
            // we can use the hasMany shortcut on the model to create a hasMany association
            hasMany: {model: 'Product', name: 'products'}
        });

        // NOTE: 'User' is not registered with the ModelManager for some reason.
        //  However, remove the 'hasMany' and it shows up ?!
        log('Model Manager', Ext.ModelManager);

        log('About to create');
        //first, we load up a User with id of 1
        var user = Ext.create('User', {id: 1, name: 'Ed'});
        log('Created ', user);

//the user.products function was created automatically by the association and returns a Store
//the created store is automatically scoped to the set of Products for the User with id of 1
        var products = user.products();

//we still have all of the usual Store functions, for example it's easy to add a Product for this User
        products.add({
            name: 'Another Product'
        });

//saves the changes to the store - this automatically sets the new Product's user_id to 1 before saving
        products.sync();

        expect(products.count()).toBe(1);
    });
    xit('personel-review example works', function() {
        // Employee Data Model
        Ext.regModel('Employee', {
            fields: [
                {name:'id', type:'int'},
                {name:'first_name', type:'string'},
                {name:'last_name', type:'string'},
                {name:'title', type:'string'}
            ],

            hasMany: {model:'Review', name:'reviews'}
        });

        // Review Data Model
        Ext.regModel('Review', {
            fields: [
                {name:'review_date', label:'Date', type:'date', dateFormat:'d-m-Y'},
                {name:'attendance', label:'Attendance', type:'int'},
                {name:'attitude', label:'Attitude', type:'int'},
                {name:'communication', label:'Communication', type:'int'},
                {name:'excellence', label:'Excellence', type:'int'},
                {name:'skills', label:'Skills', type:'int'},
                {name:'teamwork', label:'Teamwork', type:'int'},
                {name:'employee_id', label:'Employee ID', type:'int'}
            ],

            belongsTo: 'Employee'
        });

        // Instance of a Data Store to hold Employee records
        var employeeStore = new Ext.data.Store({
            storeId:'employeeStore',
            model:'Employee',
            data:[
                {id:1, first_name:'Michael', last_name:'Scott', title:'Regional Manager'},
                {id:2, first_name:'Dwight', last_name:'Schrute', title:'Sales Rep'},
                {id:3, first_name:'Jim', last_name:'Halpert', title:'Sales Rep'},
                {id:4, first_name:'Pam', last_name:'Halpert', title:'Office Administrator'},
                {id:5, first_name:'Andy', last_name:'Bernard', title:'Sales Rep'},
                {id:6, first_name:'Stanley', last_name:'Hudson', title:'Sales Rep'},
                {id:7, first_name:'Phyllis', last_name:'Lapin-Vance', title:'Sales Rep'},
                {id:8, first_name:'Kevin', last_name:'Malone', title:'Accountant'},
                {id:9, first_name:'Angela', last_name:'Martin', title:'Senior Accountant'},
                {id:10, first_name:'Meredith', last_name:'Palmer', title:'Supplier Relations Rep'}
            ],
            autoLoad:true
        });

        var reviewStore = new Ext.data.Store({
            storeId:'reviewStore',
            model:'Review',
            data:[
                {review_date:'01-04-2011', attendance:10, attitude:6, communication:6, excellence:3, skills:3, teamwork:3, employee_id:1},
                {review_date:'01-04-2011', attendance:6, attitude:5, communication:2, excellence:8, skills:9, teamwork:5, employee_id:2},
                {review_date:'01-04-2011', attendance:5, attitude:4, communication:3, excellence:5, skills:6, teamwork:2, employee_id:3},
                {review_date:'01-04-2011', attendance:8, attitude:2, communication:4, excellence:2, skills:5, teamwork:6, employee_id:4},
                {review_date:'01-04-2011', attendance:4, attitude:1, communication:5, excellence:7, skills:5, teamwork:5, employee_id:5},
                {review_date:'01-04-2011', attendance:5, attitude:2, communication:4, excellence:7, skills:9, teamwork:8, employee_id:6},
                {review_date:'01-04-2011', attendance:10, attitude:7, communication:8, excellence:7, skills:3, teamwork:4, employee_id:7},
                {review_date:'01-04-2011', attendance:10, attitude:8, communication:8, excellence:4, skills:8, teamwork:7, employee_id:8},
                {review_date:'01-04-2011', attendance:6, attitude:4, communication:9, excellence:7, skills:6, teamwork:5, employee_id:9},
                {review_date:'01-04-2011', attendance:7, attitude:5, communication:9, excellence:4, skills:2, teamwork:4, employee_id:10}
            ]
//        listeners: {
//            add:function(store, records, storeIndex) {
//                var radarStore = Ext.data.StoreMgr.lookup('radarStore');
//
//                if(radarStore) {    // only add records if an instance of the rardarStore already exists
//                    radarStore.addUpdateRecordFromReviews(records);   // add a new radarStore records for new review records
//                }
//            }, // end add listener
//            update: function(store, record, operation) {
//                radarStore.addUpdateRecordFromReviews([record]);
//                refreshRadarChart();
//            },
//            remove: function(store, records, storeIndex) {
//                // update the radarStore and regenerate the radarChart
//                Ext.data.StoreMgr.lookup('radarStore').removeRecordFromReviews(records);
//                refreshRadarChart();
//            } // end remove listener
//        }
        });

        expect(reviewStore.count()).toBe(10);
    });
    it('instantiates', function() {
        var p = Hierarchy.parent();
        expect(p).toBeDefined();
    });
    it('generates ids', function() {
        var p = Hierarchy.parent();
        expect(p).toBeDefined();
        specCheck();
        expect(p.get('id')).toBeDefined();
        specCheck();
        var p2 = Hierarchy.parent();
        expect(p.get('id')).not.toBe(p2.get('id'));
        specCheck();
    });
    it('works with a store', function() {
        var data = [
            {
                id: 'p1',
                name: 'Parent One'
            },
            {
                id: 'p2',
                name: 'Parent Two'
            }
        ];

        var store = Ext.create('Ext.data.Store', {
            model: 'LE.model.hierarchy.Parent',
            data: data
        });

        expect(store.count()).toBe(2);
    });
});
