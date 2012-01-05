// TODO: Tidy up
describe("Crud infrastructure", function() {
    it("can create a store with jsonp proxy", function() {
        var EI = testApp();
        log("Created EI ", EI);
        var store = Ext.create('EI.store.NumStrMap');
        log("Created store: ", store);
        expect(store).toBeDefined();
        store.load(function(records, operation, success) {
            log('loaded records', records);
            expect(store.getTotalCount()).toBe(1);
            var first = records[0];
            log("First ", first);
            log("num ", first.get("num"));
        });
    });
});

