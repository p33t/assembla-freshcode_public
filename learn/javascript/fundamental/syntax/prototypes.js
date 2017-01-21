// prototypes.js
"use strict";

// Object.prototype is at the root

// An object constructor function; is the prototype object
// Can use 'new ...()'
// Captialised by convention
function Circle(diameter) {
    this.diameter = diameter;
}

const c1 = new Circle(21)

console.log(c1.diameter)

c1.instOnlyProp = "I'm only on the c1 instance"
c1.instOnlyMethod = function () {
    return "Operating only on the instance"
}

function Location(x, y) {
    this.x = x
    this.y = y
}

// added to all circle instances
// only modify one's own prototypes (never standard JS objects')
Circle.prototype.loc = new Location(0, 0)
Circle.prototype.calcArea = function () {
    var rad = this.diameter / 2
    return 3.1415 * rad * rad
}

console.log(c1.loc.x)
console.log("area = " + c1.calcArea())


// .apply() vs .call()
Circle.prototype.assignLoc = function (x, y) {
    this.loc = new Location(x, y)
}

Circle.prototype.assignLoc.call(c1, 1, 1)
console.log(c1.loc.x)

const arr = [2, 2]
Circle.prototype.assignLoc.apply(c1, arr)
console.log(c1.loc.x)


