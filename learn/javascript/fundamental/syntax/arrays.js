// arrays.js
"use strict";

// don't use: new Array()
const arr = []
arr.push("one", "two") // instead of arr[arr.length] = "one"
console.log("arr: " + arr) // prints 'arr: one,two'

// spread operator to do copy (instead of long way)
const arrCopy = [...arr]
console.log("arrCopy: " + arrCopy) // prints 'arrCopy: one,two'

// 'array-like' objects to an array (Like Document.querySelectorAll())
const arrFrom = Array.from("abc")
console.log("arrFrom: " + arrFrom) // prints 'arrFrom: a,b,c'

// array method callbacks (use a return unless it's a one-liner)
console.log([1, 2, 3].map((x) => x + 1)) // prints '[ 2, 3, 4 ]'
const basic = [1, 2, 3].reduce((soFar, next) => soFar + next, 0)
console.log("basic: " + basic) // prints 'basic: 6'
const elaborate = [1, 2, 3].reduce((soFar, next) => {
        const x = soFar + next
        return x
    }, 0)
console.log("elaborate: " + elaborate) // prints 'elaborate: 6'
