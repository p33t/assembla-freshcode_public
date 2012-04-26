package biz.freshcode.learn.gwt.client.uispike.builder.field;

import com.sencha.gxt.widget.core.client.form.TextField;
import biz.freshcode.learn.gwt.client.uispike.builder.BeanBuilder;

// Generated by BeanBuilderGenerator
@BeanBuilder(TextField.class)
public class TextFieldBuilder {
    public final TextField textField;

    public TextFieldBuilder() {
        this(new TextField());
    }

    public TextFieldBuilder(TextField v) {
        textField = v;
    }

    public TextFieldBuilder readOnly(boolean v0) {
        textField.setReadOnly(v0);
        return this;
    }

/* Generics too hard at the moment
  public TextFieldBuilder value(java.lang.Object v0) {
    textField.setValue(v0);
    return this;
  }
*/

    public TextFieldBuilder propertyEditor(com.sencha.gxt.widget.core.client.form.PropertyEditor v0) {
        textField.setPropertyEditor(v0);
        return this;
    }

    public TextFieldBuilder allowBlank(boolean v0) {
        textField.setAllowBlank(v0);
        return this;
    }

    public TextFieldBuilder cursorPos(int v0) {
        textField.setCursorPos(v0);
        return this;
    }

    public TextFieldBuilder direction(com.google.gwt.i18n.client.HasDirection.Direction v0) {
        textField.setDirection(v0);
        return this;
    }

    public TextFieldBuilder directionEstimator(boolean v0) {
        textField.setDirectionEstimator(v0);
        return this;
    }

    public TextFieldBuilder directionEstimator(com.google.gwt.i18n.shared.DirectionEstimator v0) {
        textField.setDirectionEstimator(v0);
        return this;
    }

    public TextFieldBuilder emptyText(java.lang.String v0) {
        textField.setEmptyText(v0);
        return this;
    }

    public TextFieldBuilder id(java.lang.String v0) {
        textField.setId(v0);
        return this;
    }

    public TextFieldBuilder selectionRange(int v0, int v1) {
        textField.setSelectionRange(v0, v1);
        return this;
    }

    public TextFieldBuilder selectOnFocus(boolean v0) {
        textField.setSelectOnFocus(v0);
        return this;
    }

    public TextFieldBuilder text(java.lang.String v0) {
        textField.setText(v0);
        return this;
    }

    public TextFieldBuilder name(java.lang.String v0) {
        textField.setName(v0);
        return this;
    }

/* Generics too hard at the moment
  public TextFieldBuilder value(java.lang.Object v0, boolean v1, boolean v2) {
    textField.setValue(v0, v1, v2);
    return this;
  }
*/;

    public TextFieldBuilder delegate(com.google.gwt.editor.client.EditorDelegate v0) {
        textField.setDelegate(v0);
        return this;
    }

    public TextFieldBuilder addValidator(com.sencha.gxt.widget.core.client.form.Validator v0) {
        textField.addValidator(v0);
        return this;
    }

    public TextFieldBuilder autoValidate(boolean v0) {
        textField.setAutoValidate(v0);
        return this;
    }

    public TextFieldBuilder errorSupport(com.sencha.gxt.widget.core.client.form.error.ErrorHandler v0) {
        textField.setErrorSupport(v0);
        return this;
    }

/* Generics too hard at the moment
  public TextFieldBuilder originalValue(java.lang.Object v0) {
    textField.setOriginalValue(v0);
    return this;
  }
*/;

    public TextFieldBuilder validateOnBlur(boolean v0) {
        textField.setValidateOnBlur(v0);
        return this;
    }

    public TextFieldBuilder validationDelay(int v0) {
        textField.setValidationDelay(v0);
        return this;
    }

/* Generics too hard at the moment
  public TextFieldBuilder value(java.lang.Object v0, boolean v1) {
    textField.setValue(v0, v1);
    return this;
  }
*/;

    public TextFieldBuilder size(java.lang.String v0, java.lang.String v1) {
        textField.setSize(v0, v1);
        return this;
    }

    public TextFieldBuilder position(int v0, int v1) {
        textField.setPosition(v0, v1);
        return this;
    }

    public TextFieldBuilder addStyleOnOver(com.google.gwt.dom.client.Element v0, java.lang.String v1) {
        textField.addStyleOnOver(v0, v1);
        return this;
    }

    public TextFieldBuilder allowTextSelection(boolean v0) {
        textField.setAllowTextSelection(v0);
        return this;
    }

    public TextFieldBuilder borders(boolean v0) {
        textField.setBorders(v0);
        return this;
    }

    public TextFieldBuilder bounds(int v0, int v1, int v2, int v3) {
        textField.setBounds(v0, v1, v2, v3);
        return this;
    }

    public TextFieldBuilder bounds(com.sencha.gxt.core.client.util.Rectangle v0) {
        textField.setBounds(v0);
        return this;
    }

    public TextFieldBuilder contextMenu(com.sencha.gxt.widget.core.client.menu.Menu v0) {
        textField.setContextMenu(v0);
        return this;
    }

    public TextFieldBuilder data(java.lang.String v0, java.lang.Object v1) {
        textField.setData(v0, v1);
        return this;
    }

    public TextFieldBuilder deferHeight(boolean v0) {
        textField.setDeferHeight(v0);
        return this;
    }

    public TextFieldBuilder enabled(boolean v0) {
        textField.setEnabled(v0);
        return this;
    }

    public TextFieldBuilder height(int v0) {
        textField.setHeight(v0);
        return this;
    }

    public TextFieldBuilder height(java.lang.String v0) {
        textField.setHeight(v0);
        return this;
    }

    public TextFieldBuilder hideMode(com.sencha.gxt.core.client.Style.HideMode v0) {
        textField.setHideMode(v0);
        return this;
    }

    public TextFieldBuilder itemId(java.lang.String v0) {
        textField.setItemId(v0);
        return this;
    }

    public TextFieldBuilder pagePosition(int v0, int v1) {
        textField.setPagePosition(v0, v1);
        return this;
    }

    public TextFieldBuilder pixelSize(int v0, int v1) {
        textField.setPixelSize(v0, v1);
        return this;
    }

    public TextFieldBuilder shadow(boolean v0) {
        textField.setShadow(v0);
        return this;
    }

    public TextFieldBuilder stateful(boolean v0) {
        textField.setStateful(v0);
        return this;
    }

    public TextFieldBuilder stateId(java.lang.String v0) {
        textField.setStateId(v0);
        return this;
    }

    public TextFieldBuilder tabIndex(int v0) {
        textField.setTabIndex(v0);
        return this;
    }

    public TextFieldBuilder toolTip(java.lang.String v0) {
        textField.setToolTip(v0);
        return this;
    }

    public TextFieldBuilder toolTipConfig(com.sencha.gxt.widget.core.client.tips.ToolTipConfig v0) {
        textField.setToolTipConfig(v0);
        return this;
    }

    public TextFieldBuilder visible(boolean v0) {
        textField.setVisible(v0);
        return this;
    }

    public TextFieldBuilder width(int v0) {
        textField.setWidth(v0);
        return this;
    }

    public TextFieldBuilder width(java.lang.String v0) {
        textField.setWidth(v0);
        return this;
    }

    public TextFieldBuilder layoutData(java.lang.Object v0) {
        textField.setLayoutData(v0);
        return this;
    }

    public TextFieldBuilder addStyleDependentName(java.lang.String v0) {
        textField.addStyleDependentName(v0);
        return this;
    }

    public TextFieldBuilder styleDependentName(java.lang.String v0, boolean v1) {
        textField.setStyleDependentName(v0, v1);
        return this;
    }

    public TextFieldBuilder addStyleName(java.lang.String v0) {
        textField.addStyleName(v0);
        return this;
    }

    public TextFieldBuilder styleName(java.lang.String v0, boolean v1) {
        textField.setStyleName(v0, v1);
        return this;
    }

    public TextFieldBuilder styleName(java.lang.String v0) {
        textField.setStyleName(v0);
        return this;
    }

    public TextFieldBuilder stylePrimaryName(java.lang.String v0) {
        textField.setStylePrimaryName(v0);
        return this;
    }

    public TextFieldBuilder title(java.lang.String v0) {
        textField.setTitle(v0);
        return this;
    }
}