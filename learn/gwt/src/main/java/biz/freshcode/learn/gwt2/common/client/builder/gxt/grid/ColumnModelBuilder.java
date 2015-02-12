package biz.freshcode.learn.gwt2.common.client.builder.gxt.grid;

import com.sencha.gxt.widget.core.client.grid.ColumnModel;

/**
 * Generated by BeanBuilderGenerator
 *
 * @see com.sencha.gxt.widget.core.client.grid.ColumnModel
 */
@SuppressWarnings("UnusedDeclaration")
@biz.freshcode.learn.gwt2.common.client.builder.BeanBuilder(ColumnModel.class)
public class ColumnModelBuilder<M> extends biz.freshcode.learn.gwt2.common.client.builder.Construct.Parent<ColumnModelBuilder<M>> {
    public final ColumnModel<M> columnModel;

    public ColumnModelBuilder(ColumnModel<M> v) {
        columnModel = v;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnModel#ColumnModel(java.util.List)
     */
    public ColumnModelBuilder(java.util.List<com.sencha.gxt.widget.core.client.grid.ColumnConfig<M, ?>> v0) {
        this(new ColumnModel<M>(v0));
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnModel#addAggregationRow(com.sencha.gxt.widget.core.client.grid.AggregationRowConfig)
     */
    public ColumnModelBuilder<M> addAggregationRow(com.sencha.gxt.widget.core.client.grid.AggregationRowConfig<M> v0) {
        columnModel.addAggregationRow(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnModel#addHeaderGroup(int, int, com.sencha.gxt.widget.core.client.grid.HeaderGroupConfig)
     */
    public ColumnModelBuilder<M> addHeaderGroup(int v0, int v1, com.sencha.gxt.widget.core.client.grid.HeaderGroupConfig v2) {
        columnModel.addHeaderGroup(v0, v1, v2);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnModel#setColumnHeader(int, com.google.gwt.safehtml.shared.SafeHtml)
     */
    public ColumnModelBuilder<M> columnHeader(int v0, com.google.gwt.safehtml.shared.SafeHtml v1) {
        columnModel.setColumnHeader(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnModel#setColumnWidth(int, int)
     */
    public ColumnModelBuilder<M> columnWidth(int v0, int v1) {
        columnModel.setColumnWidth(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnModel#setColumnWidth(int, int, boolean)
     */
    public ColumnModelBuilder<M> columnWidth(int v0, int v1, boolean v2) {
        columnModel.setColumnWidth(v0, v1, v2);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnModel#setHidden(int, boolean)
     */
    public ColumnModelBuilder<M> hidden(int v0, boolean v1) {
        columnModel.setHidden(v0, v1);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.grid.ColumnModel#setUserResized(boolean)
     */
    public ColumnModelBuilder<M> userResized(boolean v0) {
        columnModel.setUserResized(v0);
        return this;
    }
}