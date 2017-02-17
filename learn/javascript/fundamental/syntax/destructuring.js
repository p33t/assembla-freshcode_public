// destructuring.js
"use strict";

// destructuring to avoid temporary references
function FullName(givenName, familyName) {
    this.givenName = givenName
    this.familyName = familyName
}

const bruceLee = new FullName("Bruce", "Lee")
const {givenName, familyName} = bruceLee // variables must have the same name
console.log(`destructure: ${givenName} ${familyName}`)

function formatFullName({givenName, familyName}) { // args must have the same name
    return `${familyName}, ${givenName}`
}
console.log("formatted: " + formatFullName(bruceLee))

// destructure an array (or part there of)
const [first, second] = ["1st", "2nd", "3rd"]
console.log("first: " + first + ", second: " + second)

// destructure for multiple return values (use objects, not an array which relies on ordering)
function dimension() {
    const left = "<"
    const right = ">"
    return {left, right}
}

const {right} = dimension() // we only want the right
console.log("right: " + right)
