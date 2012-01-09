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

        waitsFor(recordsArePresent, "There are no records loaded or server took too long to respond.", 200);
        // NOTE: Don't put any code outside a 'runs'... it will be run immediately

        runs(function() {
            expect(recordsArePresent()).toBe(true);
            specCheck();
            var first = store.getAt(0);
            expect(first.get('num')).toBeDefined();
            expect(first.get('str')).toBeDefined();
        });
    });
});

