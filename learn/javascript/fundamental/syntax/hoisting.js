// hoisting.js
"use strict";

v1 = "first value"

function f1() {
    console.log(`v1: ${v1}`)
    var v1 = "inner value"
}

f1() // The function declaration is hoisted over the assignment so this prints 'undefined'
var v1 // hoisted to start of scope
var v2 = v1 // just the declaration is hoisted, not the assignment

console.log(`v2: ${v2}`)
