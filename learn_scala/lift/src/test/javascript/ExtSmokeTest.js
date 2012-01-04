describe("Ext infrastructure", function() {
    it("is available", function() {
        return expect(Ext).toBeDefined();
    });

    it("can load the application", function() {
        return expect(EI).toBeTruthy();
    });

    // NOTE: This may fail without a doctored ExtJS... see this projects READ_ME.txt
    it("will supply a specified model and does not suffer the cross origin request error", function() {
        var u = Ext.create("EI.model.User");
        return expect(u).toBeTruthy();
    });
});
