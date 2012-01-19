describe('Parent model', function() {
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
