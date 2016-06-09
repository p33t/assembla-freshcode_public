package biz.freshcode.learn.gwt2.common.client.builder.gxt.grid;

import com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig;

/**
 * Generated by BeanBuilderGenerator
 *
 * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig
 */
@SuppressWarnings("UnusedDeclaration")
@biz.freshcode.learn.gwt2.common.client.builder.BeanBuilder(SummaryColumnConfig.class)
public class SummaryColumnConfigBuilder<M, N> extends biz.freshcode.learn.gwt2.common.client.builder.Construct.Parent<SummaryColumnConfigBuilder<M, N>> {
    public final SummaryColumnConfig<M, N> summaryColumnConfig;

    public SummaryColumnConfigBuilder(SummaryColumnConfig<M, N> v) {
        summaryColumnConfig = v;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#SummaryColumnConfig(com.sencha.gxt.core.client.ValueProvider)
     */
    public SummaryColumnConfigBuilder(com.sencha.gxt.core.client.ValueProvider<? super M, N> v0) {
        this(new SummaryColumnConfig<M, N>(v0));
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#SummaryColumnConfig(com.sencha.gxt.core.client.ValueProvider, int)
     */
    public SummaryColumnConfigBuilder(com.sencha.gxt.core.client.ValueProvider<? super M, N> v0, int v1) {
        this(new SummaryColumnConfig<M, N>(v0, v1));
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#SummaryColumnConfig(com.sencha.gxt.core.client.ValueProvider, int, java.lang.String)
     */
    public SummaryColumnConfigBuilder(com.sencha.gxt.core.client.ValueProvider<? super M, N> v0, int v1, java.lang.String v2) {
        this(new SummaryColumnConfig<M, N>(v0, v1, v2));
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#SummaryColumnConfig(com.sencha.gxt.core.client.ValueProvider, int, com.google.gwt.safehtml.shared.SafeHtml)
     */
    public SummaryColumnConfigBuilder(com.sencha.gxt.core.client.ValueProvider<? super M, N> v0, int v1, com.google.gwt.safehtml.shared.SafeHtml v2) {
        this(new SummaryColumnConfig<M, N>(v0, v1, v2));
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setCell(com.google.gwt.cell.client.Cell)
     */
    public SummaryColumnConfigBuilder<M, N> cell(com.google.gwt.cell.client.Cell<N> v0) {
        summaryColumnConfig.setCell(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setCellClassName(java.lang.String)
     */
    public SummaryColumnConfigBuilder<M, N> cellClassName(java.lang.String v0) {
        summaryColumnConfig.setCellClassName(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setCellPadding(boolean)
     */
    public SummaryColumnConfigBuilder<M, N> cellPadding(boolean v0) {
        summaryColumnConfig.setCellPadding(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setColumnHeaderClassName(java.lang.String)
     */
    public SummaryColumnConfigBuilder<M, N> columnHeaderClassName(java.lang.String v0) {
        summaryColumnConfig.setColumnHeaderClassName(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setColumnStyle(com.google.gwt.safecss.shared.SafeStyles)
     */
    public SummaryColumnConfigBuilder<M, N> columnStyle(com.google.gwt.safecss.shared.SafeStyles v0) {
        summaryColumnConfig.setColumnStyle(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setColumnTextClassName(java.lang.String)
     */
    public SummaryColumnConfigBuilder<M, N> columnTextClassName(java.lang.String v0) {
        summaryColumnConfig.setColumnTextClassName(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setColumnTextStyle(com.google.gwt.safecss.shared.SafeStyles)
     */
    public SummaryColumnConfigBuilder<M, N> columnTextStyle(com.google.gwt.safecss.shared.SafeStyles v0) {
        summaryColumnConfig.setColumnTextStyle(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setComparator(java.util.Comparator)
     */
    public SummaryColumnConfigBuilder<M, N> comparator(java.util.Comparator<N> v0) {
        summaryColumnConfig.setComparator(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setFixed(boolean)
     */
    public SummaryColumnConfigBuilder<M, N> fixed(boolean v0) {
        summaryColumnConfig.setFixed(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setGroupable(boolean)
     */
    public SummaryColumnConfigBuilder<M, N> groupable(boolean v0) {
        summaryColumnConfig.setGroupable(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setHeader(java.lang.String)
     */
    public SummaryColumnConfigBuilder<M, N> header(java.lang.String v0) {
        summaryColumnConfig.setHeader(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setHeader(com.google.gwt.safehtml.shared.SafeHtml)
     */
    public SummaryColumnConfigBuilder<M, N> header(com.google.gwt.safehtml.shared.SafeHtml v0) {
        summaryColumnConfig.setHeader(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setHidden(boolean)
     */
    public SummaryColumnConfigBuilder<M, N> hidden(boolean v0) {
        summaryColumnConfig.setHidden(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setHideable(boolean)
     */
    public SummaryColumnConfigBuilder<M, N> hideable(boolean v0) {
        summaryColumnConfig.setHideable(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setHorizontalAlignment(com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant)
     */
    public SummaryColumnConfigBuilder<M, N> horizontalAlignment(com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant v0) {
        summaryColumnConfig.setHorizontalAlignment(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setHorizontalHeaderAlignment(com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant)
     */
    public SummaryColumnConfigBuilder<M, N> horizontalHeaderAlignment(com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant v0) {
        summaryColumnConfig.setHorizontalHeaderAlignment(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setMenuDisabled(boolean)
     */
    public SummaryColumnConfigBuilder<M, N> menuDisabled(boolean v0) {
        summaryColumnConfig.setMenuDisabled(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setResizable(boolean)
     */
    public SummaryColumnConfigBuilder<M, N> resizable(boolean v0) {
        summaryColumnConfig.setResizable(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setRowHeader(boolean)
     */
    public SummaryColumnConfigBuilder<M, N> rowHeader(boolean v0) {
        summaryColumnConfig.setRowHeader(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setSortable(boolean)
     */
    public SummaryColumnConfigBuilder<M, N> sortable(boolean v0) {
        summaryColumnConfig.setSortable(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setSummaryFormat(com.google.gwt.i18n.client.NumberFormat)
     */
    public SummaryColumnConfigBuilder<M, N> summaryFormat(com.google.gwt.i18n.client.NumberFormat v0) {
        summaryColumnConfig.setSummaryFormat(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setSummaryRenderer(com.sencha.gxt.widget.core.client.grid.SummaryRenderer)
     */
    public SummaryColumnConfigBuilder<M, N> summaryRenderer(com.sencha.gxt.widget.core.client.grid.SummaryRenderer<M> v0) {
        summaryColumnConfig.setSummaryRenderer(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setSummaryType(com.sencha.gxt.widget.core.client.grid.SummaryType)
     */
    public SummaryColumnConfigBuilder<M, N> summaryType(com.sencha.gxt.widget.core.client.grid.SummaryType<N, ?> v0) {
        summaryColumnConfig.setSummaryType(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setToolTip(java.lang.String)
     */
    public SummaryColumnConfigBuilder<M, N> toolTip(java.lang.String v0) {
        summaryColumnConfig.setToolTip(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setToolTip(com.google.gwt.safehtml.shared.SafeHtml)
     */
    public SummaryColumnConfigBuilder<M, N> toolTip(com.google.gwt.safehtml.shared.SafeHtml v0) {
        summaryColumnConfig.setToolTip(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setVerticalAlignment(com.google.gwt.user.client.ui.HasVerticalAlignment.VerticalAlignmentConstant)
     */
    public SummaryColumnConfigBuilder<M, N> verticalAlignment(com.google.gwt.user.client.ui.HasVerticalAlignment.VerticalAlignmentConstant v0) {
        summaryColumnConfig.setVerticalAlignment(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setWidget(com.google.gwt.user.client.ui.Widget, com.google.gwt.safehtml.shared.SafeHtml)
     */
    public SummaryColumnConfigBuilder<M, N> widget(com.google.gwt.user.client.ui.Widget v0, com.google.gwt.safehtml.shared.SafeHtml v1) {
        summaryColumnConfig.setWidget(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig#setWidth(int)
     */
    public SummaryColumnConfigBuilder<M, N> width(int v0) {
        summaryColumnConfig.setWidth(v0);
        return this;
    }
}
