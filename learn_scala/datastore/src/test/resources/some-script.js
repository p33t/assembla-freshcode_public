
var env = {
    myFunction: function(arg0, arg1) {
        console.log('arg0 = ' + arg0 + ', arg1 = ' + arg1);
    }
};

//[assign-real-env]

env.myFunction("zero", "one");
