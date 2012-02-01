/**
 * A number between 0 and 1 behind the scenes but a whole percent number to the user.
 */
var Percent = {
    REG_EXP: /^ *(\d?\d|100)%? *$/,
    toText: function(d) {
        return '' + Math.round(d * 100) + '%';
    },
    fromText: function(s) {
        var match = Percent.REG_EXP.exec(s);
        if (match === null) return NaN; // not sure about this
        return parseInt(match[1]) / 100;
    }
};
