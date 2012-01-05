
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
        store.load(function(records, operation, success) {
            expect(store.getTotalCount()).toBeGreaterThan(0);
            var first = records[0];
            expect(first.get('num')).toBeDefined();
            expect(first.get('str')).toBeDefined();
        });
    });
});

