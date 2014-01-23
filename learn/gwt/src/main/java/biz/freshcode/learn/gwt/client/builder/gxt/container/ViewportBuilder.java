package biz.freshcode.learn.gwt.client.builder.gxt.container;

import biz.freshcode.learn.gwt.client.builder.BeanBuilder;
import biz.freshcode.learn.gwt.client.builder.Construct;
import com.sencha.gxt.widget.core.client.container.Viewport;

/**
 * Generated by BeanBuilderGenerator
 *
 * @see com.sencha.gxt.widget.core.client.container.Viewport
 */
@BeanBuilder(Viewport.class)
@SuppressWarnings("UnusedDeclaration")
public class ViewportBuilder extends Construct.Parent<ViewportBuilder> {
    public final Viewport viewport;

    public ViewportBuilder(Viewport v) {
        viewport = v;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#Viewport()
     */
    public ViewportBuilder() {
        this(new Viewport());
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#Viewport(com.sencha.gxt.widget.core.client.container.Viewport.ViewportAppearance)
     */
    public ViewportBuilder(com.sencha.gxt.widget.core.client.container.Viewport.ViewportAppearance v0) {
        this(new Viewport(v0));
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#add(com.google.gwt.user.client.ui.Widget)
     */
    public ViewportBuilder add(com.google.gwt.user.client.ui.Widget v0) {
        viewport.add(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#add(com.google.gwt.user.client.ui.IsWidget)
     */
    public ViewportBuilder add(com.google.gwt.user.client.ui.IsWidget v0) {
        viewport.add(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#add(com.google.gwt.user.client.ui.Widget, com.sencha.gxt.widget.core.client.container.MarginData)
     */
    public ViewportBuilder add(com.google.gwt.user.client.ui.Widget v0, com.sencha.gxt.widget.core.client.container.MarginData v1) {
        viewport.add(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#addStyleDependentName(java.lang.String)
     */
    public ViewportBuilder addStyleDependentName(java.lang.String v0) {
        viewport.addStyleDependentName(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#addStyleName(java.lang.String)
     */
    public ViewportBuilder addStyleName(java.lang.String v0) {
        viewport.addStyleName(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#addStyleOnOver(com.google.gwt.dom.client.Element, java.lang.String)
     */
    public ViewportBuilder addStyleOnOver(com.google.gwt.dom.client.Element v0, java.lang.String v1) {
        viewport.addStyleOnOver(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setAllowTextSelection(boolean)
     */
    public ViewportBuilder allowTextSelection(boolean v0) {
        viewport.setAllowTextSelection(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setBorders(boolean)
     */
    public ViewportBuilder borders(boolean v0) {
        viewport.setBorders(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setBounds(com.sencha.gxt.core.client.util.Rectangle)
     */
    public ViewportBuilder bounds(com.sencha.gxt.core.client.util.Rectangle v0) {
        viewport.setBounds(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setBounds(int, int, int, int)
     */
    public ViewportBuilder bounds(int v0, int v1, int v2, int v3) {
        viewport.setBounds(v0, v1, v2, v3);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setContextMenu(com.sencha.gxt.widget.core.client.menu.Menu)
     */
    public ViewportBuilder contextMenu(com.sencha.gxt.widget.core.client.menu.Menu v0) {
        viewport.setContextMenu(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setData(java.lang.String, java.lang.Object)
     */
    public ViewportBuilder data(java.lang.String v0, java.lang.Object v1) {
        viewport.setData(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setDeferHeight(boolean)
     */
    public ViewportBuilder deferHeight(boolean v0) {
        viewport.setDeferHeight(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setEnableScroll(boolean)
     */
    public ViewportBuilder enableScroll(boolean v0) {
        viewport.setEnableScroll(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setEnabled(boolean)
     */
    public ViewportBuilder enabled(boolean v0) {
        viewport.setEnabled(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setHeight(java.lang.String)
     */
    public ViewportBuilder height(java.lang.String v0) {
        viewport.setHeight(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setHeight(int)
     */
    public ViewportBuilder height(int v0) {
        viewport.setHeight(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setHideMode(com.sencha.gxt.core.client.Style.HideMode)
     */
    public ViewportBuilder hideMode(com.sencha.gxt.core.client.Style.HideMode v0) {
        viewport.setHideMode(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setId(java.lang.String)
     */
    public ViewportBuilder id(java.lang.String v0) {
        viewport.setId(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setItemId(java.lang.String)
     */
    public ViewportBuilder itemId(java.lang.String v0) {
        viewport.setItemId(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setLayoutData(java.lang.Object)
     */
    public ViewportBuilder layoutData(java.lang.Object v0) {
        viewport.setLayoutData(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setPagePosition(int, int)
     */
    public ViewportBuilder pagePosition(int v0, int v1) {
        viewport.setPagePosition(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setPixelSize(int, int)
     */
    public ViewportBuilder pixelSize(int v0, int v1) {
        viewport.setPixelSize(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setPosition(int, int)
     */
    public ViewportBuilder position(int v0, int v1) {
        viewport.setPosition(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setResize(boolean)
     */
    public ViewportBuilder resize(boolean v0) {
        viewport.setResize(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setShadow(boolean)
     */
    public ViewportBuilder shadow(boolean v0) {
        viewport.setShadow(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setShadowPosition(com.sencha.gxt.core.client.dom.Layer.ShadowPosition)
     */
    public ViewportBuilder shadowPosition(com.sencha.gxt.core.client.dom.Layer.ShadowPosition v0) {
        viewport.setShadowPosition(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setSize(java.lang.String, java.lang.String)
     */
    public ViewportBuilder size(java.lang.String v0, java.lang.String v1) {
        viewport.setSize(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setStateId(java.lang.String)
     */
    public ViewportBuilder stateId(java.lang.String v0) {
        viewport.setStateId(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setStateful(boolean)
     */
    public ViewportBuilder stateful(boolean v0) {
        viewport.setStateful(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setStyleDependentName(java.lang.String, boolean)
     */
    public ViewportBuilder styleDependentName(java.lang.String v0, boolean v1) {
        viewport.setStyleDependentName(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setStyleName(java.lang.String)
     */
    public ViewportBuilder styleName(java.lang.String v0) {
        viewport.setStyleName(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setStyleName(java.lang.String, boolean)
     */
    public ViewportBuilder styleName(java.lang.String v0, boolean v1) {
        viewport.setStyleName(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setStylePrimaryName(java.lang.String)
     */
    public ViewportBuilder stylePrimaryName(java.lang.String v0) {
        viewport.setStylePrimaryName(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setTabIndex(int)
     */
    public ViewportBuilder tabIndex(int v0) {
        viewport.setTabIndex(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setTitle(java.lang.String)
     */
    public ViewportBuilder title(java.lang.String v0) {
        viewport.setTitle(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setToolTip(java.lang.String)
     */
    public ViewportBuilder toolTip(java.lang.String v0) {
        viewport.setToolTip(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setToolTipConfig(com.sencha.gxt.widget.core.client.tips.ToolTipConfig)
     */
    public ViewportBuilder toolTipConfig(com.sencha.gxt.widget.core.client.tips.ToolTipConfig v0) {
        viewport.setToolTipConfig(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setVisible(boolean)
     */
    public ViewportBuilder visible(boolean v0) {
        viewport.setVisible(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setWidget(com.google.gwt.user.client.ui.IsWidget)
     */
    public ViewportBuilder widget(com.google.gwt.user.client.ui.IsWidget v0) {
        viewport.setWidget(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setWidget(com.google.gwt.user.client.ui.Widget)
     */
    public ViewportBuilder widget(com.google.gwt.user.client.ui.Widget v0) {
        viewport.setWidget(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setWidth(java.lang.String)
     */
    public ViewportBuilder width(java.lang.String v0) {
        viewport.setWidth(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.Viewport#setWidth(int)
     */
    public ViewportBuilder width(int v0) {
        viewport.setWidth(v0);
        return this;
    }
}