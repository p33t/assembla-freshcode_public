describe('config read/write fields work', function() {
    var MyClass = undefined;
    beforeEach(function() {
        // declare the class
        if (MyClass === undefined) {
            MyClass = Ext.define('MyClass', {
                config: {
                    str: 'default str',
                    num: 99
                },
                // Why is this necessary?
                constructor: function(config) {
                    this.initConfig(config);
                }
            });
            log('Defined class', MyClass);
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
});