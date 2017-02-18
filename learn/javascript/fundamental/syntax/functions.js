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
