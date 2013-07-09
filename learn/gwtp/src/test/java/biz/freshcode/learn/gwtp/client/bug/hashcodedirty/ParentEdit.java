package biz.freshcode.learn.gwtp.client.bug.hashcodedirty;

import com.google.gwt.editor.client.Editor;

public class ParentEdit implements Editor<Parent> {
    final AltHashSetEditor alts = new AltHashSetEditor();
}
