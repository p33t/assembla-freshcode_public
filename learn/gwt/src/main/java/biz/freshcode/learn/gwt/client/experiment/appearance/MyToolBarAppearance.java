/**
 * A modified copy of BlueToolBarAppearance.java
 */
package biz.freshcode.learn.gwt.client.experiment.appearance;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.theme.base.client.toolbar.ToolBarBaseAppearance;

public class MyToolBarAppearance extends ToolBarBaseAppearance {

  public interface Resources extends ClientBundle {
    @Source({"com/sencha/gxt/theme/base/client/toolbar/ToolBarBase.css", "MyToolBar.css"})
    Style style();
  }

  public interface Style extends ToolBarBaseStyle, CssResource {
      String toolBar();

      @ClassName("x-toolbar-mark")
      String xToolbarMark();
  }

  private final Style style;
  private final Resources resources;

  public MyToolBarAppearance() {
    this(GWT.<Resources> create(Resources.class));
  }

  public MyToolBarAppearance(Resources resources) {
    this.resources = resources;
    this.style = this.resources.style();
   
    StyleInjectorHelper.ensureInjected(style, true);
  }

  @Override
  public String toolBarClassName() {
    return style.toolBar();
  }

}
