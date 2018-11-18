Fundamentals / Style / Syntax for Javascript

Guides x10:
http://noeticforce.com/best-javascript-style-guide-for-maintainable-code

Good JS Intro/Refresh:
https://www.w3schools.com/js/js_function_definition.asp

Notes =======================================================================================
Language ------------------------------------------------------------------------------------
- "use strict"; at top of script requires variables to be declared (as of ES5, ignored in older browsers)
- Function 'declaration' vs 'expression'.  Expression function has a semicolon whereas declaration 'should' not.
- Expression function results in an 'anonymous' function (no name)
- Function constructor can also be used to create function from strings (but typically not necessary)
- Hoisting (of functions and variables to top of current scope) means functions can be used before they are declared
-- Expressions assigned to a variable are not, so expression functions are not hoisted (var decl and init are split)
- Functions are objects (of type function); they have properties and methods
- Function 'parameters' are the names listed in the definition
- Function 'arguments' are the values passed to the function (via parameters)
- Functions are always methods on an object, which may be the default global object. (EG: 'window' for browsers)
- 'this' is a keyword (not a variable) that references the owner of the current code. (EG: for funct body it is the obj)
- All functions are methods (owner object defaults to global object)
- call() invokes a function with a supplied 'this' and args.
- apply() is similar to call() except it takes an arg array (IE: Can delegate function call easily using 'arguments' implicit variable)
- ??? function can be invoked without being called? https://www.w3schools.com/js/js_function_invocation.asp
- A JS closure has access to a parent scope, even after the parent function has closed


Style ---------------------------------------------------------------------------------------
- Apparently 'single' quotes are better than "double".  But not my preference
- Don't use multi line strings ('\' at end of line).  'Tis troublesome.
- Never 'eval()' a string
-
