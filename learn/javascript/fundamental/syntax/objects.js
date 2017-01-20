// objects.js
"use strict";

function calcName(ip) {
  return "m" + ip
}

const someConst = "someConst-value"

const myObj = { // don't use new Object()
  someConst // shorthand properties at top, no need for someConst: someConst
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

TODO: 
console.log(Object.prototype.hasOwnProperty.call(myObj, key));

