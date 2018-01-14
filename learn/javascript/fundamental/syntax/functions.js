// functions.js
"use strict";

const f1 = function f1Def() {
    // This is a function expression (with name 'f1Def' for better callstack info) assigned to a constant.
    // Contrast this with a function declaration which is hoisted (entirely) above var assignments, which is error prone.
}

// IIFE = Immediately Invoked Function Expression
// Are best placed in parens to indicate a single unit
// These are rarely needed nowadays due to modules
(function () {
    console.log("IIFE")
}())

// Assign function to variable inside a non-function block (don't declare the function)
let customFn
if (true) {
    customFn = () => console.log("Yay, it's set")
}

customFn()

// never use 'arguments' as a parameter, it obscures the object given to each function scope
// 'arguments' is array-like (not a real Array)
function notArguments(v1, v2, args) {
}

// but better not to 'arguments' anyway.  Use '...args' to get a real array
function spreadArg(...args) {
    console.log("There are " + args.length + " args")
    console.log(...args)
}

spreadArg(1, 2, 3, 4)

// default value for an arg (don't alter an args value)
function defArgs(arg0 = "implicit") {
    console.log("arg0 = " + arg0)
}

defArgs("explicit")
defArgs()

// put defaults at end of arg list
function badDefault(arg0 = "implicit", arg1) {
    console.log(arg0, arg1)
}

// don't use Function constructor directly
let naughty = Function('a', 'b', 'return a - b')
console.log("Should be 5: " + (naughty(10, 5)))


// new object for a function with 'call'
let objWithFunc = {
    nickname: "bazza",
    assignNickname: function (altNickname) {
        let old = this.nickname;
        this.nickname = altNickname;
        return old;
    }
}
console.log("Should be 'bazza': " + objWithFunc.nickname)
objWithFunc.assignNickname("shazza")
console.log("Should be 'shazza': " + objWithFunc.nickname)

let objSansFunc = {
    nickname: "gazza"
}
objWithFunc.assignNickname.call(objSansFunc, "dazza")
console.log("Should be 'dazza': " + objSansFunc.nickname)

// function constructor
function funcConstructor(attribZero) {
    this.attrib0 = attribZero
}

// noinspection JSPotentiallyInvalidConstructorUsage
let constructed = new funcConstructor("zzz") // constructor invocation returning an object
console.log("Function constructor returns an object.  Should be 'zzz': " + constructed.attrib0)

// a closure has access to a parent scope, even after the parent function has closed
// it can be used to implement hidden global variables
let secureCounter = (function () {
    let youCantTouchThis = 0
    return function () {
        return youCantTouchThis += 1;
    }
})();
console.log("One: " + secureCounter())
console.log("Two: " + secureCounter())
