function numStrStore() {
    var EI = testApp();
//    log("Created EI ", EI);
    return Ext.create('EI.store.NumStrStore');
}

describe("Crud infrastructure", function() {
    it("can create a store with jsonp proxy", function() {
        var store = numStrStore();
        expect(store).toBeDefined();
    });
    it("can load at least one record that has a num and a str", function() {
        var store = numStrStore();

        function recordsArePresent() {
            var total = store.getTotalCount();
            if (total === undefined) return false;
            return total > 0;
        }

        expect(recordsArePresent()).toBe(false);
        specCheck();
        store.load(function(records, operation, success) {
//            log('Load complete: ', arguments);
        });

        waitsFor(recordsArePresent, "There are no records loaded or server took too long to respond.", TEST_CONFIG.ajaxTimeout);
        // NOTE: Don't put any code outside a 'runs'... it will be run immediately

        runs(function() {
            expect(recordsArePresent()).toBe(true);
            specCheck();
            var first = store.getAt(0);
            expect(first.get('num')).toBeDefined();
            expect(first.get('str')).toBeDefined();
        });
    });
    it ("saving a new record... BAD", function() {
        var store = numStrStore();
        var rec = Ext.create("EI.model.NumStr");
        rec.set('num', 99);
        rec.set('str', 'Ninety-nine');
        log("Finished setting values on ", rec);
        store.add(rec);
        log("Record added");
        store.sync();
        log("store sync()");
    });
    it ("model can create new records... BAD", function() {
        var EI = testApp();
//       Ext.require("EI.model.NumStr");
       var rec = Ext.create("EI.model.NumStr");
       rec.set('num', 99);
       rec.set('str', 'Ninety-nine');
       log("Finished setting values on ", rec);
       var result = undefined;
       var op = //Ext.create('Ext.data.Operation',
           {
           action: 'create',
           callback: function(records, operation, success) {
               log("callback with success: ", success);
               result = success;
           }
       };
       log ("Calling operation ", op);
       rec.save(op);
       log ("Called.");

       function resultDefined() {
           log("Examining result ", result);
           return typeof result != 'undefined';
       }

       waitsFor(resultDefined, "Saving of record took too long.", TEST_CONFIG.ajaxTimeout);

       runs(function() {
           expect(resultDefined()).toBe(true);
           specCheck();
           expect(result).toBe(true);
           specCheck();
       })
    });
});

