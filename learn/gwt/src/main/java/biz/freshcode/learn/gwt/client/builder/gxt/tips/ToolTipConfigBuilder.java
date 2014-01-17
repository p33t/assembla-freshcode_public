package biz.freshcode.learn.gwt.client.builder.gxt.tips;

import biz.freshcode.learn.gwt.client.builder.BeanBuilder;
import biz.freshcode.learn.gwt.client.builder.Construct;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;

/**
 * Generated by BeanBuilderGenerator
 *
 * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig
 */
@BeanBuilder(ToolTipConfig.class)
@SuppressWarnings("UnusedDeclaration")
public class ToolTipConfigBuilder extends Construct.Parent<ToolTipConfigBuilder> {
    public final ToolTipConfig toolTipConfig;

    public ToolTipConfigBuilder(ToolTipConfig v) {
        toolTipConfig = v;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#ToolTipConfig(java.lang.String, java.lang.String)
     */
    public ToolTipConfigBuilder(java.lang.String v0, java.lang.String v1) {
        this(new ToolTipConfig(v0, v1));
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#ToolTipConfig(java.lang.String)
     */
    public ToolTipConfigBuilder(java.lang.String v0) {
        this(new ToolTipConfig(v0));
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#ToolTipConfig()
     */
    public ToolTipConfigBuilder() {
        this(new ToolTipConfig());
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setAnchor(com.sencha.gxt.core.client.Style.Side)
     */
    public ToolTipConfigBuilder anchor(com.sencha.gxt.core.client.Style.Side v0) {
        toolTipConfig.setAnchor(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setAnchorOffset(int)
     */
    public ToolTipConfigBuilder anchorOffset(int v0) {
        toolTipConfig.setAnchorOffset(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setAnchorToTarget(boolean)
     */
    public ToolTipConfigBuilder anchorToTarget(boolean v0) {
        toolTipConfig.setAnchorToTarget(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setAutoHide(boolean)
     */
    public ToolTipConfigBuilder autoHide(boolean v0) {
        toolTipConfig.setAutoHide(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setBodyHtml(com.google.gwt.safehtml.shared.SafeHtml)
     */
    public ToolTipConfigBuilder bodyHtml(com.google.gwt.safehtml.shared.SafeHtml v0) {
        toolTipConfig.setBodyHtml(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setBodyHtml(java.lang.String)
     */
    public ToolTipConfigBuilder bodyHtml(java.lang.String v0) {
        toolTipConfig.setBodyHtml(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setBodyText(java.lang.String)
     */
    public ToolTipConfigBuilder bodyText(java.lang.String v0) {
        toolTipConfig.setBodyText(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setCloseable(boolean)
     */
    public ToolTipConfigBuilder closeable(boolean v0) {
        toolTipConfig.setCloseable(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setData(java.lang.Object)
     */
    public ToolTipConfigBuilder data(java.lang.Object v0) {
        toolTipConfig.setData(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setDismissDelay(int)
     */
    public ToolTipConfigBuilder dismissDelay(int v0) {
        toolTipConfig.setDismissDelay(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setEnabled(boolean)
     */
    public ToolTipConfigBuilder enabled(boolean v0) {
        toolTipConfig.setEnabled(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setHideDelay(int)
     */
    public ToolTipConfigBuilder hideDelay(int v0) {
        toolTipConfig.setHideDelay(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setMaxWidth(int)
     */
    public ToolTipConfigBuilder maxWidth(int v0) {
        toolTipConfig.setMaxWidth(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setMinWidth(int)
     */
    public ToolTipConfigBuilder minWidth(int v0) {
        toolTipConfig.setMinWidth(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setMouseOffset(int[])
     */
    public ToolTipConfigBuilder mouseOffset(int[] v0) {
        toolTipConfig.setMouseOffset(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setMouseOffsetX(int)
     */
    public ToolTipConfigBuilder mouseOffsetX(int v0) {
        toolTipConfig.setMouseOffsetX(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setMouseOffsetY(int)
     */
    public ToolTipConfigBuilder mouseOffsetY(int v0) {
        toolTipConfig.setMouseOffsetY(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setRenderer(com.sencha.gxt.widget.core.client.tips.ToolTipConfig.ToolTipRenderer)
     */
    public ToolTipConfigBuilder renderer(com.sencha.gxt.widget.core.client.tips.ToolTipConfig.ToolTipRenderer<?> v0) {
        toolTipConfig.setRenderer(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setShowDelay(int)
     */
    public ToolTipConfigBuilder showDelay(int v0) {
        toolTipConfig.setShowDelay(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setTitleHtml(java.lang.String)
     */
    public ToolTipConfigBuilder titleHtml(java.lang.String v0) {
        toolTipConfig.setTitleHtml(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setTitleHtml(com.google.gwt.safehtml.shared.SafeHtml)
     */
    public ToolTipConfigBuilder titleHtml(com.google.gwt.safehtml.shared.SafeHtml v0) {
        toolTipConfig.setTitleHtml(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setTitleText(java.lang.String)
     */
    public ToolTipConfigBuilder titleText(java.lang.String v0) {
        toolTipConfig.setTitleText(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.tips.ToolTipConfig#setTrackMouse(boolean)
     */
    public ToolTipConfigBuilder trackMouse(boolean v0) {
        toolTipConfig.setTrackMouse(v0);
        return this;
    }
}
