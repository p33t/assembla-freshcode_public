package biz.freshcode.learn.gwt.client.uispike.builder;

import com.google.gwt.user.client.ui.MenuBar;

// Generated by BeanBuilderGenerator
@BeanBuilder(MenuBar.class)
public class MenuBarBuilder {
    public final MenuBar menuBar;

    public MenuBarBuilder() {
        this(new MenuBar());
    }

    public MenuBarBuilder(MenuBar v) {
        menuBar = v;
    }

    public MenuBarBuilder addItem(com.google.gwt.user.client.ui.MenuItem v0) {
        menuBar.addItem(v0);
        return this;
    }

    public MenuBarBuilder addSeparator(com.google.gwt.user.client.ui.MenuItemSeparator v0) {
        menuBar.addSeparator(v0);
        return this;
    }

    public MenuBarBuilder animationEnabled(boolean v0) {
        menuBar.setAnimationEnabled(v0);
        return this;
    }

    public MenuBarBuilder autoOpen(boolean v0) {
        menuBar.setAutoOpen(v0);
        return this;
    }

    public MenuBarBuilder focusOnHoverEnabled(boolean v0) {
        menuBar.setFocusOnHoverEnabled(v0);
        return this;
    }

    public MenuBarBuilder layoutData(java.lang.Object v0) {
        menuBar.setLayoutData(v0);
        return this;
    }

    public MenuBarBuilder size(java.lang.String v0, java.lang.String v1) {
        menuBar.setSize(v0, v1);
        return this;
    }

    public MenuBarBuilder styleName(java.lang.String v0, boolean v1) {
        menuBar.setStyleName(v0, v1);
        return this;
    }

    public MenuBarBuilder styleName(java.lang.String v0) {
        menuBar.setStyleName(v0);
        return this;
    }

    public MenuBarBuilder addStyleDependentName(java.lang.String v0) {
        menuBar.addStyleDependentName(v0);
        return this;
    }

    public MenuBarBuilder addStyleName(java.lang.String v0) {
        menuBar.addStyleName(v0);
        return this;
    }

    public MenuBarBuilder visible(boolean v0) {
        menuBar.setVisible(v0);
        return this;
    }

    public MenuBarBuilder stylePrimaryName(java.lang.String v0) {
        menuBar.setStylePrimaryName(v0);
        return this;
    }

    public MenuBarBuilder height(java.lang.String v0) {
        menuBar.setHeight(v0);
        return this;
    }

    public MenuBarBuilder pixelSize(int v0, int v1) {
        menuBar.setPixelSize(v0, v1);
        return this;
    }

    public MenuBarBuilder styleDependentName(java.lang.String v0, boolean v1) {
        menuBar.setStyleDependentName(v0, v1);
        return this;
    }

    public MenuBarBuilder title(java.lang.String v0) {
        menuBar.setTitle(v0);
        return this;
    }

    public MenuBarBuilder width(java.lang.String v0) {
        menuBar.setWidth(v0);
        return this;
    }
}
