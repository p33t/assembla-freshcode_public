An Ext JS learning project.

Download Ext JS SDK, unpackage to a .../web/extjs-X.X folder (beware a sym link may not work)

Also, there is a line of code in extjs that causes jasmine:test to error with
  * SyntaxError: missing } after function body in file:/home/pleong/work/learn/extjs/target/jasmine/src/extjs-4.0/ext-debug.js#7821(Function) (line 5)
The offending line of code is
  new Function(xhr.responseText + "\n//@ sourceURL=" + fileName)();
and the error goes away when it is changed to
  new Function(xhr.responseText)();
IE: Take out the extra line and special comment: + "\n//@ sourceURL=" + fileName
