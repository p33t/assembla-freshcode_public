An Ext JS learning project.

Download Ext JS SDK, unpackage to a .../web/extjs-X.X folder (beware a sym link may not work)

Jasmine does NOT like symbolic links to the extjs-X.X folder.

Also, there is a line of code in extjs that causes jasmine:test to error with
  * SyntaxError: missing } after function body in file:/home/pleong/work/learn/extjs/target/jasmine/src/extjs-4.0/ext-debug.js#7821(Function) (line 5)
The offending line of code is
  new Function(xhr.responseText + "\n//@ sourceURL=" + fileName)();
and the error goes away when it is changed to
  new Function(xhr.responseText)();
IE: Take out the extra line and special comment: + "\n//@ sourceURL=" + fileName

Some user extensions have been added:
- examples/ux/form & layout have been copied to src/ux/... (Extra layout components needed in 4.0.7 but not in 4.1)
- examples/ux/css/images/(x6).gif & ItemSelector.css "dido"
- ItemSelector.onAddBtnClick() need 'this.checkChange();' appended.  There is a bug which appears to have been fixed in 4.1.0.
