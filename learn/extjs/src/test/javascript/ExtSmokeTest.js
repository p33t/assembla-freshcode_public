describe("Ext infrastructure", function() {
    it("is available", function() {
        return expect(Ext).toBeDefined();
    });

    it("can load the application", function() {
        var pathToRoot = calcPathToRoot();
        var LE = Ext.create('Ext.app.Application', {
            name: 'LE',
            appFolder: pathToRoot + '/app'
        });
        return expect(LE).toBeTruthy();
    });

    // NOTE: This may fail without a doctored ExtJS... see this projects READ_ME.txt
    it("will supply a specified model and does not suffer the cross origin request error", function() {
        var a = Ext.create("LE.model.Appointment");
        return expect(a).toBeTruthy();
    });
});
