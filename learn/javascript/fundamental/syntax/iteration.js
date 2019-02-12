"use strict";

class MyClass {
    constructor() {
        this.elems = [];
    }

    [Symbol.iterator]() {
        return this.elems[Symbol.iterator]();
    }

    add(x) {
        this.elems.push(x);
    }
}

const myClass = new MyClass();

myClass.add("a");
myClass.add("b");

for (const e of myClass) console.log(e);

// Nope
// myClass.forEach(console.log);


