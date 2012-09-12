package biz.freshcode.learn.gwt.client.builder.gxt.grid;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * Generated by BeanBuilderGenerator
 *
 * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig
 */
@biz.freshcode.learn.gwt.client.uispike.builder.BeanBuilder(ColumnConfig.class)
public class ColumnConfigBuilder<M, N> extends biz.freshcode.learn.gwt.client.uispike.builder.Construct.Parent<ColumnConfigBuilder<M, N>> {
    public final ColumnConfig<M, N> columnConfig;

    public ColumnConfigBuilder(ColumnConfig<M, N> v) {
        columnConfig = v;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#ColumnConfig(com.sencha.gxt.core.client.ValueProvider)
     */
    public ColumnConfigBuilder(com.sencha.gxt.core.client.ValueProvider<? super M, N> v0) {
        this(new ColumnConfig<M, N>(v0));
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#ColumnConfig(com.sencha.gxt.core.client.ValueProvider, int)
     */
    public ColumnConfigBuilder(com.sencha.gxt.core.client.ValueProvider<? super M, N> v0, int v1) {
        this(new ColumnConfig<M, N>(v0, v1));
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#ColumnConfig(com.sencha.gxt.core.client.ValueProvider, int, com.google.gwt.safehtml.shared.SafeHtml)
     */
    public ColumnConfigBuilder(com.sencha.gxt.core.client.ValueProvider<? super M, N> v0, int v1, com.google.gwt.safehtml.shared.SafeHtml v2) {
        this(new ColumnConfig<M, N>(v0, v1, v2));
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#ColumnConfig(com.sencha.gxt.core.client.ValueProvider, int, java.lang.String)
     */
    public ColumnConfigBuilder(com.sencha.gxt.core.client.ValueProvider<? super M, N> v0, int v1, java.lang.String v2) {
        this(new ColumnConfig<M, N>(v0, v1, v2));
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setAlignment(com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant)
     */
    public ColumnConfigBuilder<M, N> alignment(com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant v0) {
        columnConfig.setAlignment(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setCell(com.google.gwt.cell.client.Cell)
     */
    public ColumnConfigBuilder<M, N> cell(com.google.gwt.cell.client.Cell<N> v0) {
        columnConfig.setCell(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setColumnClassSuffix(java.lang.String)
     */
    public ColumnConfigBuilder<M, N> columnClassSuffix(java.lang.String v0) {
        columnConfig.setColumnClassSuffix(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setColumnHeaderClassName(java.lang.String)
     */
    public ColumnConfigBuilder<M, N> columnHeaderClassName(java.lang.String v0) {
        columnConfig.setColumnHeaderClassName(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setColumnStyle(com.google.gwt.safecss.shared.SafeStyles)
     */
    public ColumnConfigBuilder<M, N> columnStyle(com.google.gwt.safecss.shared.SafeStyles v0) {
        columnConfig.setColumnStyle(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setColumnTextClassName(java.lang.String)
     */
    public ColumnConfigBuilder<M, N> columnTextClassName(java.lang.String v0) {
        columnConfig.setColumnTextClassName(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setColumnTextStyle(com.google.gwt.safecss.shared.SafeStyles)
     */
    public ColumnConfigBuilder<M, N> columnTextStyle(com.google.gwt.safecss.shared.SafeStyles v0) {
        columnConfig.setColumnTextStyle(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setComparator(java.util.Comparator)
     */
    public ColumnConfigBuilder<M, N> comparator(java.util.Comparator<N> v0) {
        columnConfig.setComparator(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setFixed(boolean)
     */
    public ColumnConfigBuilder<M, N> fixed(boolean v0) {
        columnConfig.setFixed(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setGroupable(boolean)
     */
    public ColumnConfigBuilder<M, N> groupable(boolean v0) {
        columnConfig.setGroupable(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setHeader(com.google.gwt.safehtml.shared.SafeHtml)
     */
    public ColumnConfigBuilder<M, N> header(com.google.gwt.safehtml.shared.SafeHtml v0) {
        columnConfig.setHeader(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setHeader(java.lang.String)
     */
    public ColumnConfigBuilder<M, N> header(java.lang.String v0) {
        columnConfig.setHeader(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setHidden(boolean)
     */
    public ColumnConfigBuilder<M, N> hidden(boolean v0) {
        columnConfig.setHidden(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setHideable(boolean)
     */
    public ColumnConfigBuilder<M, N> hideable(boolean v0) {
        columnConfig.setHideable(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setMenuDisabled(boolean)
     */
    public ColumnConfigBuilder<M, N> menuDisabled(boolean v0) {
        columnConfig.setMenuDisabled(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setResizable(boolean)
     */
    public ColumnConfigBuilder<M, N> resizable(boolean v0) {
        columnConfig.setResizable(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setRowHeader(boolean)
     */
    public ColumnConfigBuilder<M, N> rowHeader(boolean v0) {
        columnConfig.setRowHeader(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setSortable(boolean)
     */
    public ColumnConfigBuilder<M, N> sortable(boolean v0) {
        columnConfig.setSortable(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setToolTip(com.google.gwt.safehtml.shared.SafeHtml)
     */
    public ColumnConfigBuilder<M, N> toolTip(com.google.gwt.safehtml.shared.SafeHtml v0) {
        columnConfig.setToolTip(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setWidget(com.google.gwt.user.client.ui.Widget, com.google.gwt.safehtml.shared.SafeHtml)
     */
    public ColumnConfigBuilder<M, N> widget(com.google.gwt.user.client.ui.Widget v0, com.google.gwt.safehtml.shared.SafeHtml v1) {
        columnConfig.setWidget(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnConfig#setWidth(int)
     */
    public ColumnConfigBuilder<M, N> width(int v0) {
        columnConfig.setWidth(v0);
        return this;
    }
}
