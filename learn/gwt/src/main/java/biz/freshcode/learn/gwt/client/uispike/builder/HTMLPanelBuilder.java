package biz.freshcode.learn.gwt.client.uispike.builder;

import com.google.gwt.user.client.ui.HTMLPanel;

// Generated by BeanBuilderGenerator
@BeanBuilder(HTMLPanel.class)
public class HTMLPanelBuilder {
    public final HTMLPanel hTMLPanel;

    public HTMLPanelBuilder(HTMLPanel v) {
        hTMLPanel = v;
    }

    public HTMLPanelBuilder layoutData(java.lang.Object v0) {
        hTMLPanel.setLayoutData(v0);
        return this;
    }

    public HTMLPanelBuilder size(java.lang.String v0, java.lang.String v1) {
        hTMLPanel.setSize(v0, v1);
        return this;
    }

    public HTMLPanelBuilder visible(boolean v0) {
        hTMLPanel.setVisible(v0);
        return this;
    }

    public HTMLPanelBuilder styleName(java.lang.String v0, boolean v1) {
        hTMLPanel.setStyleName(v0, v1);
        return this;
    }

    public HTMLPanelBuilder styleName(java.lang.String v0) {
        hTMLPanel.setStyleName(v0);
        return this;
    }

    public HTMLPanelBuilder stylePrimaryName(java.lang.String v0) {
        hTMLPanel.setStylePrimaryName(v0);
        return this;
    }

    public HTMLPanelBuilder height(java.lang.String v0) {
        hTMLPanel.setHeight(v0);
        return this;
    }

    public HTMLPanelBuilder pixelSize(int v0, int v1) {
        hTMLPanel.setPixelSize(v0, v1);
        return this;
    }

    public HTMLPanelBuilder styleDependentName(java.lang.String v0, boolean v1) {
        hTMLPanel.setStyleDependentName(v0, v1);
        return this;
    }

    public HTMLPanelBuilder title(java.lang.String v0) {
        hTMLPanel.setTitle(v0);
        return this;
    }

    public HTMLPanelBuilder width(java.lang.String v0) {
        hTMLPanel.setWidth(v0);
        return this;
    }

    public HTMLPanelBuilder initFrom(HTMLPanel v) {
        return this
                .layoutData(v.getLayoutData())
                .styleName(v.getStyleName())
                .stylePrimaryName(v.getStylePrimaryName())
                .title(v.getTitle());
    }
}
