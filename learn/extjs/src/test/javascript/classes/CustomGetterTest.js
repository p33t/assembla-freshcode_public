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
                    // this same technique can be used for overriding setters etc.
                    this.getStrOrig = this.getStr;
                    this.getStr = function() {
                        var s = this.getStrOrig();
                        log('getStr() called.  Returning ', s);
                        return s;
                    };
                }
            });
        }
    });

    it('dodgy override works', function() {
        var mc = Ext.create('MyClass', {str: 'bruce'});
        // instantiate twice in case prototype was disturbed
        var mc2 = Ext.create('MyClass', {str: 'lee'});
//        log(mc2);
        expect(mc.getStr()).toBe('bruce');
        expect(mc2.getStr()).toBe('lee');
    });
});