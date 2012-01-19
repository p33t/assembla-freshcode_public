
function parent() {
    return Ext.create('LE.model.hierarchy.Parent');
}

describe('Parent model', function() {
    it('instantiates', function() {
        var p = parent();
        expect(p).toBeDefined();
    });
    it('generates ids', function() {
        var p = parent();
        expect(p).toBeDefined();
        specCheck();
        expect(p.get('id')).toBeDefined();
        specCheck();
        var p2 = parent();
        expect(p.get('id')).not.toBe(p2.get('id'));
        specCheck();
    });
});
