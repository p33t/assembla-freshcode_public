var Epoch = new Date(0);
var Day = 24 * 60 * 60 * 1000;
/**
 * This can be used for period description.  The ISO-8601 Weeknumber is in our favour.
 */
var Day0 = new Date(-3 * Day);
describe('Date exploration', function() {
    it('epoch', function() {
        expect(Ext.Date.format(Epoch, 'W')).toBe('01');
        expect(Ext.Date.format(Epoch, 'D')).toBe('Thu');
    });
    it('day0', function() {
        expect(Ext.Date.format(Day0, 'W')).toBe('01');
        expect(Ext.Date.format(Day0, 'D')).toBe('Mon');
    });
});