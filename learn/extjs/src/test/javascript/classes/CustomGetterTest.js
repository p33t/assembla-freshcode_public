describe('Getter override', function() {
    var created = false;
    beforeEach(function() {
        // declare the class
        if (!created) {
            created = true;
            Ext.define('MyClass', {
                config: {
                    str: 'default str'
                },
                constructor: function(config) {
                    this.initConfig(config);
                    // this same technique can be used for overriding getters etc.
                    var orig = this.getStr;
                    this.getStr = function() {
                        var s = orig.call(this);
                        log('getStr() called.  Returning ', s);
                        return s;
                    };
                }
            });
        }
    });

    it('dodgy override works', function() {
        var mc = Ext.create('MyClass', {str: 'bruce'});
        expect(mc.getStr()).toBe('bruce');
    });
});