// variables.js
"strict mode"; // don't use this style in codebases in transition to strict.  Do function by function.

const myConst = "x"
// no compile...myConst = "y"

{
    let myLet = "b" // use const or let before var
    var myVar = "a"
}

console.log("myVar = " + myVar) // only var is not block scoped
// no compile...console.log("myLet = " + myLet)

const arr = [1, 2]
const arr2 = arr // assigns a reference (not deep copy)
arr[0] = 3
arr2[1] = 4

console.log("arr = " + arr) // prints 3, 4

// variables can be global or local.  Not using 'var' or 'let' causes global variable always.
let globalVar = "gb"

function scopeFunction() {
    let localVar = "lv"
    globalVar = globalVar + "_alt"
    // noinspection JSUndeclaredVariable
    gl0balVar = "typo created global"
    console.log("Should be 'gb_alt': " + globalVar)
    console.log("gl0balVar: " + gl0balVar)
}
scopeFunction()

