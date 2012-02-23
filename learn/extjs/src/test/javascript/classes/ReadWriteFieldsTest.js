describe('config read/write fields work', function() {
    var MyClass = undefined;
    beforeEach(function() {
        // declare the class
        if (MyClass === undefined) {
            MyClass = Ext.define('MyClass', {
                config: {
                    str: 'default str',
                    num: 99,
                    funct: function(msg) {
                        log("Default funct ", msg);
                    }
                },
                // Why is this necessary?
                constructor: function(config) {
                    this.initConfig(config);
                },
                altGetStr: function() {
                    return this.str;
                }
            });
            log('Defined MyClass as', MyClass);
        }
    });

    it('boots');

    it('can instantiate', function() {
        var v = Ext.create('MyClass');
        expect(v).toBeDefined();
    });

    it('has getter', function() {
        var v = Ext.create('MyClass', {
            str: 'custom str'
        });

        expect(v.getStr()).toBe('custom str');
        expect(v.getNum()).toBe(99);
        specCheck();
    });

    it ('works with function vars', function() {
        var custom = function(msg) {
            log("Custom funct", msg);
        };
        var v = Ext.create('MyClass' , {funct: custom});
        expect(v.getFunct()).toBeDefined();
        specCheck();
        v.getFunct()('testing');
        expect(v.getFunct()).toBe(custom);
    });

    it ('getter is not that special', function() {
        var v = Ext.create('MyClass');
        expect(v.getStr()).toBe('default str');
        expect(v.altGetStr()).toBe('default str');
        v.setStr('bruce');
        expect(v.getStr()).toBe('bruce');
        expect(v.altGetStr()).toBe('bruce');
    });
});