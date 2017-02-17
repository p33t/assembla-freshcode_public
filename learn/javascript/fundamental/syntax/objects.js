// objects.js
"use strict";

function calcName(ip) {
  return "m" + ip
}

const someConst = "someConst-value"

const myObj = { // don't use new Object()
  someConst, // shorthand properties at top, no need for someConst: someConst
  id: 99,
  [calcName(1)]: "m1-value", // declare calculated property names within block
  myFunc(ip) { // don't use myFunc: function(ip) {
    return ip + ip
  },
  'special-prop': "special-prop-value" // only use single quotes where necessary
}

console.log("myObj = " + myObj)
console.log("myObj.m1 = " + myObj.m1)
console.log("myObj.myFunc(2) = " + myObj.myFunc(2))
console.log("myObj.someConst = " + myObj.someConst)

// invoke Object.prototype methods via 'call/apply' in case they are shadowed on the object
console.log(Object.prototype.hasOwnProperty.call(myObj, "someProp"));
// NOT myObj.hasOwnProperty(..)

// spread operator for shallow copy + add properties
const arr12 = [1, 2]
const spreadArr = [...arr12, 3, 4]
console.log("spreadArr: " + spreadArr)

const obj12 = {one: 1, two: 2}
// No can do... const spreadObj = {...obj12, three: 3, four: 4}
// console.log("spreadObj: " + spreadObj)
// Prefer ... 'spread' operator over Object.assign() for shallow copying

// rest operator
// Does not compile...
// const {hasOne, ...hasRest} = obj12
// console.log("hasOne: " + hasOne)
// console.log("hasRest: " + hasRest)

