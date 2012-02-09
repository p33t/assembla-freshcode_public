// NOTE: It is not possible to escape from the current time zone (?!)
var Epoch_NOT = new Date(0);
var Day = 24 * 60 * 60 * 1000;
/**
 * This can be used for period description.  The ISO-8601 Weeknumber is in our favour.
 */
var Day0 = new Date(-3 * Day);
function fmt(date, pattern) {return Ext.Date.format(date, pattern);}
describe('Date exploration', function() {
    it('epoch', function() {
        expect(fmt(Epoch_NOT, 'W')).toBe('01');
        expect(fmt(Epoch_NOT, 'D')).toBe('Thu');
        //expect(fmt(Epoch_NOT, 'H')).toBe('00');
    });
    it('day0', function() {
        expect(fmt(Day0, 'W')).toBe('01');
        expect(fmt(Day0, 'D')).toBe('Mon');
    });
    itx('utc', function() {
        expect(fmt(Epoch_NOT, 'Z')).toBe('0');
    });
});