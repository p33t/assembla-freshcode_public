package biz.freshcode.learn.gwt.client.uispike.builder.container;

import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import biz.freshcode.learn.gwt.client.uispike.builder.BeanBuilder;

// Generated by BeanBuilderGenerator
@BeanBuilder(FlowLayoutContainer.class)
public class FlowLayoutContainerBuilder {
    public final FlowLayoutContainer flowLayoutContainer;

    public FlowLayoutContainerBuilder() {
        this(new FlowLayoutContainer());
    }

    public FlowLayoutContainerBuilder(FlowLayoutContainer v) {
        flowLayoutContainer = v;
    }

    public FlowLayoutContainerBuilder add(com.google.gwt.user.client.ui.IsWidget v0, com.sencha.gxt.widget.core.client.container.MarginData v1) {
        flowLayoutContainer.add(v0, v1);
        return this;
    }

    public FlowLayoutContainerBuilder scrollMode(com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode v0) {
        flowLayoutContainer.setScrollMode(v0);
        return this;
    }

    public FlowLayoutContainerBuilder scrollSupport(com.sencha.gxt.core.client.dom.ScrollSupport v0) {
        flowLayoutContainer.setScrollSupport(v0);
        return this;
    }

    public FlowLayoutContainerBuilder add(com.google.gwt.user.client.ui.IsWidget v0) {
        flowLayoutContainer.add(v0);
        return this;
    }

    public FlowLayoutContainerBuilder add(com.google.gwt.user.client.ui.Widget v0) {
        flowLayoutContainer.add(v0);
        return this;
    }

    public FlowLayoutContainerBuilder size(java.lang.String v0, java.lang.String v1) {
        flowLayoutContainer.setSize(v0, v1);
        return this;
    }

    public FlowLayoutContainerBuilder addStyleOnOver(com.google.gwt.dom.client.Element v0, java.lang.String v1) {
        flowLayoutContainer.addStyleOnOver(v0, v1);
        return this;
    }

    public FlowLayoutContainerBuilder allowTextSelection(boolean v0) {
        flowLayoutContainer.setAllowTextSelection(v0);
        return this;
    }

    public FlowLayoutContainerBuilder borders(boolean v0) {
        flowLayoutContainer.setBorders(v0);
        return this;
    }

    public FlowLayoutContainerBuilder bounds(int v0, int v1, int v2, int v3) {
        flowLayoutContainer.setBounds(v0, v1, v2, v3);
        return this;
    }

    public FlowLayoutContainerBuilder bounds(com.sencha.gxt.core.client.util.Rectangle v0) {
        flowLayoutContainer.setBounds(v0);
        return this;
    }

    public FlowLayoutContainerBuilder contextMenu(com.sencha.gxt.widget.core.client.menu.Menu v0) {
        flowLayoutContainer.setContextMenu(v0);
        return this;
    }

    public FlowLayoutContainerBuilder data(java.lang.String v0, java.lang.Object v1) {
        flowLayoutContainer.setData(v0, v1);
        return this;
    }

    public FlowLayoutContainerBuilder deferHeight(boolean v0) {
        flowLayoutContainer.setDeferHeight(v0);
        return this;
    }

    public FlowLayoutContainerBuilder enabled(boolean v0) {
        flowLayoutContainer.setEnabled(v0);
        return this;
    }

    public FlowLayoutContainerBuilder height(int v0) {
        flowLayoutContainer.setHeight(v0);
        return this;
    }

    public FlowLayoutContainerBuilder height(java.lang.String v0) {
        flowLayoutContainer.setHeight(v0);
        return this;
    }

    public FlowLayoutContainerBuilder hideMode(com.sencha.gxt.core.client.Style.HideMode v0) {
        flowLayoutContainer.setHideMode(v0);
        return this;
    }

    public FlowLayoutContainerBuilder id(java.lang.String v0) {
        flowLayoutContainer.setId(v0);
        return this;
    }

    public FlowLayoutContainerBuilder itemId(java.lang.String v0) {
        flowLayoutContainer.setItemId(v0);
        return this;
    }

    public FlowLayoutContainerBuilder pagePosition(int v0, int v1) {
        flowLayoutContainer.setPagePosition(v0, v1);
        return this;
    }

    public FlowLayoutContainerBuilder pixelSize(int v0, int v1) {
        flowLayoutContainer.setPixelSize(v0, v1);
        return this;
    }

    public FlowLayoutContainerBuilder position(int v0, int v1) {
        flowLayoutContainer.setPosition(v0, v1);
        return this;
    }

    public FlowLayoutContainerBuilder shadow(boolean v0) {
        flowLayoutContainer.setShadow(v0);
        return this;
    }

    public FlowLayoutContainerBuilder stateful(boolean v0) {
        flowLayoutContainer.setStateful(v0);
        return this;
    }

    public FlowLayoutContainerBuilder stateId(java.lang.String v0) {
        flowLayoutContainer.setStateId(v0);
        return this;
    }

    public FlowLayoutContainerBuilder tabIndex(int v0) {
        flowLayoutContainer.setTabIndex(v0);
        return this;
    }

    public FlowLayoutContainerBuilder toolTip(java.lang.String v0) {
        flowLayoutContainer.setToolTip(v0);
        return this;
    }

    public FlowLayoutContainerBuilder toolTipConfig(com.sencha.gxt.widget.core.client.tips.ToolTipConfig v0) {
        flowLayoutContainer.setToolTipConfig(v0);
        return this;
    }

    public FlowLayoutContainerBuilder visible(boolean v0) {
        flowLayoutContainer.setVisible(v0);
        return this;
    }

    public FlowLayoutContainerBuilder width(int v0) {
        flowLayoutContainer.setWidth(v0);
        return this;
    }

    public FlowLayoutContainerBuilder width(java.lang.String v0) {
        flowLayoutContainer.setWidth(v0);
        return this;
    }

    public FlowLayoutContainerBuilder layoutData(java.lang.Object v0) {
        flowLayoutContainer.setLayoutData(v0);
        return this;
    }

    public FlowLayoutContainerBuilder addStyleDependentName(java.lang.String v0) {
        flowLayoutContainer.addStyleDependentName(v0);
        return this;
    }

    public FlowLayoutContainerBuilder styleDependentName(java.lang.String v0, boolean v1) {
        flowLayoutContainer.setStyleDependentName(v0, v1);
        return this;
    }

    public FlowLayoutContainerBuilder addStyleName(java.lang.String v0) {
        flowLayoutContainer.addStyleName(v0);
        return this;
    }

    public FlowLayoutContainerBuilder styleName(java.lang.String v0, boolean v1) {
        flowLayoutContainer.setStyleName(v0, v1);
        return this;
    }

    public FlowLayoutContainerBuilder styleName(java.lang.String v0) {
        flowLayoutContainer.setStyleName(v0);
        return this;
    }

    public FlowLayoutContainerBuilder stylePrimaryName(java.lang.String v0) {
        flowLayoutContainer.setStylePrimaryName(v0);
        return this;
    }

    public FlowLayoutContainerBuilder title(java.lang.String v0) {
        flowLayoutContainer.setTitle(v0);
        return this;
    }
}