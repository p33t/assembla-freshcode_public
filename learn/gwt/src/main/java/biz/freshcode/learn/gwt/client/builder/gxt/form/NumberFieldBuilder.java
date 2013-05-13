package biz.freshcode.learn.gwt.client.builder.gxt.form;

import com.sencha.gxt.widget.core.client.form.NumberField;

/**
 * Generated by BeanBuilderGenerator
 *
 * @see com.sencha.gxt.widget.core.client.form.NumberField
 */
@biz.freshcode.learn.gwt.client.uispike.builder.BeanBuilder(NumberField.class)
public class NumberFieldBuilder<N extends Number> extends biz.freshcode.learn.gwt.client.uispike.builder.Construct.Parent<NumberFieldBuilder<N>> {
    public final NumberField<N> numberField;

    public NumberFieldBuilder(NumberField<N> v) {
        numberField = v;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#NumberField(com.sencha.gxt.cell.core.client.form.NumberInputCell, com.sencha.gxt.widget.core.client.form.NumberPropertyEditor)
     */
    public NumberFieldBuilder(com.sencha.gxt.cell.core.client.form.NumberInputCell<N> v0, com.sencha.gxt.widget.core.client.form.NumberPropertyEditor<N> v1) {
        this(new NumberField<N>(v0, v1));
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#NumberField(com.sencha.gxt.widget.core.client.form.NumberPropertyEditor)
     */
    public NumberFieldBuilder(com.sencha.gxt.widget.core.client.form.NumberPropertyEditor<N> v0) {
        this(new NumberField<N>(v0));
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#addStyleDependentName(java.lang.String)
     */
    public NumberFieldBuilder<N> addStyleDependentName(java.lang.String v0) {
        numberField.addStyleDependentName(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#addStyleName(java.lang.String)
     */
    public NumberFieldBuilder<N> addStyleName(java.lang.String v0) {
        numberField.addStyleName(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#addStyleOnOver(com.google.gwt.dom.client.Element, java.lang.String)
     */
    public NumberFieldBuilder<N> addStyleOnOver(com.google.gwt.dom.client.Element v0, java.lang.String v1) {
        numberField.addStyleOnOver(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#addValidator(com.sencha.gxt.widget.core.client.form.Validator)
     */
    public NumberFieldBuilder<N> addValidator(com.sencha.gxt.widget.core.client.form.Validator<N> v0) {
        numberField.addValidator(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setAllowBlank(boolean)
     */
    public NumberFieldBuilder<N> allowBlank(boolean v0) {
        numberField.setAllowBlank(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setAllowDecimals(boolean)
     */
    public NumberFieldBuilder<N> allowDecimals(boolean v0) {
        numberField.setAllowDecimals(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setAllowNegative(boolean)
     */
    public NumberFieldBuilder<N> allowNegative(boolean v0) {
        numberField.setAllowNegative(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setAllowTextSelection(boolean)
     */
    public NumberFieldBuilder<N> allowTextSelection(boolean v0) {
        numberField.setAllowTextSelection(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setAutoValidate(boolean)
     */
    public NumberFieldBuilder<N> autoValidate(boolean v0) {
        numberField.setAutoValidate(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setBaseChars(java.lang.String)
     */
    public NumberFieldBuilder<N> baseChars(java.lang.String v0) {
        numberField.setBaseChars(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setBorders(boolean)
     */
    public NumberFieldBuilder<N> borders(boolean v0) {
        numberField.setBorders(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setBounds(com.sencha.gxt.core.client.util.Rectangle)
     */
    public NumberFieldBuilder<N> bounds(com.sencha.gxt.core.client.util.Rectangle v0) {
        numberField.setBounds(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setBounds(int, int, int, int)
     */
    public NumberFieldBuilder<N> bounds(int v0, int v1, int v2, int v3) {
        numberField.setBounds(v0, v1, v2, v3);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setClearValueOnParseError(boolean)
     */
    public NumberFieldBuilder<N> clearValueOnParseError(boolean v0) {
        numberField.setClearValueOnParseError(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setContextMenu(com.sencha.gxt.widget.core.client.menu.Menu)
     */
    public NumberFieldBuilder<N> contextMenu(com.sencha.gxt.widget.core.client.menu.Menu v0) {
        numberField.setContextMenu(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setCursorPos(int)
     */
    public NumberFieldBuilder<N> cursorPos(int v0) {
        numberField.setCursorPos(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setData(java.lang.String, java.lang.Object)
     */
    public NumberFieldBuilder<N> data(java.lang.String v0, java.lang.Object v1) {
        numberField.setData(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setDecimalSeparator(java.lang.String)
     */
    public NumberFieldBuilder<N> decimalSeparator(java.lang.String v0) {
        numberField.setDecimalSeparator(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setDeferHeight(boolean)
     */
    public NumberFieldBuilder<N> deferHeight(boolean v0) {
        numberField.setDeferHeight(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setDelegate(com.google.gwt.editor.client.EditorDelegate)
     */
    public NumberFieldBuilder<N> delegate(com.google.gwt.editor.client.EditorDelegate<N> v0) {
        numberField.setDelegate(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setDirection(com.google.gwt.i18n.client.HasDirection.Direction)
     */
    public NumberFieldBuilder<N> direction(com.google.gwt.i18n.client.HasDirection.Direction v0) {
        numberField.setDirection(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setDirectionEstimator(boolean)
     */
    public NumberFieldBuilder<N> directionEstimator(boolean v0) {
        numberField.setDirectionEstimator(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setDirectionEstimator(com.google.gwt.i18n.shared.DirectionEstimator)
     */
    public NumberFieldBuilder<N> directionEstimator(com.google.gwt.i18n.shared.DirectionEstimator v0) {
        numberField.setDirectionEstimator(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setEditable(boolean)
     */
    public NumberFieldBuilder<N> editable(boolean v0) {
        numberField.setEditable(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setEmptyText(java.lang.String)
     */
    public NumberFieldBuilder<N> emptyText(java.lang.String v0) {
        numberField.setEmptyText(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setEnabled(boolean)
     */
    public NumberFieldBuilder<N> enabled(boolean v0) {
        numberField.setEnabled(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setErrorSupport(com.sencha.gxt.widget.core.client.form.error.ErrorHandler)
     */
    public NumberFieldBuilder<N> errorSupport(com.sencha.gxt.widget.core.client.form.error.ErrorHandler v0) {
        numberField.setErrorSupport(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setFormat(com.google.gwt.i18n.client.NumberFormat)
     */
    public NumberFieldBuilder<N> format(com.google.gwt.i18n.client.NumberFormat v0) {
        numberField.setFormat(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setHeight(int)
     */
    public NumberFieldBuilder<N> height(int v0) {
        numberField.setHeight(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setHeight(java.lang.String)
     */
    public NumberFieldBuilder<N> height(java.lang.String v0) {
        numberField.setHeight(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setHideMode(com.sencha.gxt.core.client.Style.HideMode)
     */
    public NumberFieldBuilder<N> hideMode(com.sencha.gxt.core.client.Style.HideMode v0) {
        numberField.setHideMode(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setHideTrigger(boolean)
     */
    public NumberFieldBuilder<N> hideTrigger(boolean v0) {
        numberField.setHideTrigger(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setId(java.lang.String)
     */
    public NumberFieldBuilder<N> id(java.lang.String v0) {
        numberField.setId(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setItemId(java.lang.String)
     */
    public NumberFieldBuilder<N> itemId(java.lang.String v0) {
        numberField.setItemId(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setLayoutData(java.lang.Object)
     */
    public NumberFieldBuilder<N> layoutData(java.lang.Object v0) {
        numberField.setLayoutData(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setMonitorTab(boolean)
     */
    public NumberFieldBuilder<N> monitorTab(boolean v0) {
        numberField.setMonitorTab(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setName(java.lang.String)
     */
    public NumberFieldBuilder<N> name(java.lang.String v0) {
        numberField.setName(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setOriginalValue(Object)
     */
    public NumberFieldBuilder<N> originalValue(N v0) {
        numberField.setOriginalValue(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setPagePosition(int, int)
     */
    public NumberFieldBuilder<N> pagePosition(int v0, int v1) {
        numberField.setPagePosition(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setPixelSize(int, int)
     */
    public NumberFieldBuilder<N> pixelSize(int v0, int v1) {
        numberField.setPixelSize(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setPosition(int, int)
     */
    public NumberFieldBuilder<N> position(int v0, int v1) {
        numberField.setPosition(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setPropertyEditor(com.sencha.gxt.widget.core.client.form.PropertyEditor)
     */
    public NumberFieldBuilder<N> propertyEditor(com.sencha.gxt.widget.core.client.form.PropertyEditor<N> v0) {
        numberField.setPropertyEditor(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setReadOnly(boolean)
     */
    public NumberFieldBuilder<N> readOnly(boolean v0) {
        numberField.setReadOnly(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setSelectOnFocus(boolean)
     */
    public NumberFieldBuilder<N> selectOnFocus(boolean v0) {
        numberField.setSelectOnFocus(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setSelectionRange(int, int)
     */
    public NumberFieldBuilder<N> selectionRange(int v0, int v1) {
        numberField.setSelectionRange(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setShadow(boolean)
     */
    public NumberFieldBuilder<N> shadow(boolean v0) {
        numberField.setShadow(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setSize(java.lang.String, java.lang.String)
     */
    public NumberFieldBuilder<N> size(java.lang.String v0, java.lang.String v1) {
        numberField.setSize(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setStateId(java.lang.String)
     */
    public NumberFieldBuilder<N> stateId(java.lang.String v0) {
        numberField.setStateId(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setStateful(boolean)
     */
    public NumberFieldBuilder<N> stateful(boolean v0) {
        numberField.setStateful(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setStyleDependentName(java.lang.String, boolean)
     */
    public NumberFieldBuilder<N> styleDependentName(java.lang.String v0, boolean v1) {
        numberField.setStyleDependentName(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setStyleName(java.lang.String)
     */
    public NumberFieldBuilder<N> styleName(java.lang.String v0) {
        numberField.setStyleName(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setStyleName(java.lang.String, boolean)
     */
    public NumberFieldBuilder<N> styleName(java.lang.String v0, boolean v1) {
        numberField.setStyleName(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setStylePrimaryName(java.lang.String)
     */
    public NumberFieldBuilder<N> stylePrimaryName(java.lang.String v0) {
        numberField.setStylePrimaryName(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setTabIndex(int)
     */
    public NumberFieldBuilder<N> tabIndex(int v0) {
        numberField.setTabIndex(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setText(java.lang.String)
     */
    public NumberFieldBuilder<N> text(java.lang.String v0) {
        numberField.setText(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setTitle(java.lang.String)
     */
    public NumberFieldBuilder<N> title(java.lang.String v0) {
        numberField.setTitle(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setToolTip(java.lang.String)
     */
    public NumberFieldBuilder<N> toolTip(java.lang.String v0) {
        numberField.setToolTip(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setToolTipConfig(com.sencha.gxt.widget.core.client.tips.ToolTipConfig)
     */
    public NumberFieldBuilder<N> toolTipConfig(com.sencha.gxt.widget.core.client.tips.ToolTipConfig v0) {
        numberField.setToolTipConfig(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setValidateOnBlur(boolean)
     */
    public NumberFieldBuilder<N> validateOnBlur(boolean v0) {
        numberField.setValidateOnBlur(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setValidationDelay(int)
     */
    public NumberFieldBuilder<N> validationDelay(int v0) {
        numberField.setValidationDelay(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setValue(Object)
     */
    public NumberFieldBuilder<N> value(N v0) {
        numberField.setValue(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setValue(Object, boolean)
     */
    public NumberFieldBuilder<N> value(N v0, boolean v1) {
        numberField.setValue(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setValue(Object, boolean, boolean)
     */
    public NumberFieldBuilder<N> value(N v0, boolean v1, boolean v2) {
        numberField.setValue(v0, v1, v2);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setVisible(boolean)
     */
    public NumberFieldBuilder<N> visible(boolean v0) {
        numberField.setVisible(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setWidth(int)
     */
    public NumberFieldBuilder<N> width(int v0) {
        numberField.setWidth(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.form.NumberField#setWidth(java.lang.String)
     */
    public NumberFieldBuilder<N> width(java.lang.String v0) {
        numberField.setWidth(v0);
        return this;
    }
}