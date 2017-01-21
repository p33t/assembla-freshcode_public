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

// TODO
// spread operator for shallow copy + add properties
// const source = {one: 1, two: 2}
// const enhanced = {...source, three: 3}
// console.log(enhanced.one + ".." + enhanced.three)

// rest operator

