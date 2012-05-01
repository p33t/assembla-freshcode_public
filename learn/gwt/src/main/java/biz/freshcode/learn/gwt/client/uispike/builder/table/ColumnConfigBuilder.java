package biz.freshcode.learn.gwt.client.uispike.builder.table;

import biz.freshcode.learn.gwt.client.uispike.builder.BeanBuilder;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

// Generated by BeanBuilderGenerator
@BeanBuilder(ColumnConfig.class)
public class ColumnConfigBuilder {
    public final ColumnConfig columnConfig;

    public ColumnConfigBuilder(ColumnConfig v) {
        columnConfig = v;
    }

    public ColumnConfigBuilder alignment(com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant v0) {
        columnConfig.setAlignment(v0);
        return this;
    }

    public ColumnConfigBuilder cell(com.google.gwt.cell.client.Cell v0) {
        columnConfig.setCell(v0);
        return this;
    }

    public ColumnConfigBuilder columnClassSuffix(java.lang.String v0) {
        columnConfig.setColumnClassSuffix(v0);
        return this;
    }

    public ColumnConfigBuilder columnHeaderClassName(java.lang.String v0) {
        columnConfig.setColumnHeaderClassName(v0);
        return this;
    }

    public ColumnConfigBuilder columnStyle(com.google.gwt.safecss.shared.SafeStyles v0) {
        columnConfig.setColumnStyle(v0);
        return this;
    }

    public ColumnConfigBuilder columnTextClassName(java.lang.String v0) {
        columnConfig.setColumnTextClassName(v0);
        return this;
    }

    public ColumnConfigBuilder columnTextStyle(com.google.gwt.safecss.shared.SafeStyles v0) {
        columnConfig.setColumnTextStyle(v0);
        return this;
    }

    public ColumnConfigBuilder comparator(java.util.Comparator v0) {
        columnConfig.setComparator(v0);
        return this;
    }

    public ColumnConfigBuilder fixed(boolean v0) {
        columnConfig.setFixed(v0);
        return this;
    }

    public ColumnConfigBuilder groupable(boolean v0) {
        columnConfig.setGroupable(v0);
        return this;
    }

    public ColumnConfigBuilder header(com.google.gwt.safehtml.shared.SafeHtml v0) {
        columnConfig.setHeader(v0);
        return this;
    }

    public ColumnConfigBuilder header(java.lang.String v0) {
        columnConfig.setHeader(v0);
        return this;
    }

    public ColumnConfigBuilder hidden(boolean v0) {
        columnConfig.setHidden(v0);
        return this;
    }

    public ColumnConfigBuilder menuDisabled(boolean v0) {
        columnConfig.setMenuDisabled(v0);
        return this;
    }

    public ColumnConfigBuilder resizable(boolean v0) {
        columnConfig.setResizable(v0);
        return this;
    }

    public ColumnConfigBuilder rowHeader(boolean v0) {
        columnConfig.setRowHeader(v0);
        return this;
    }

    public ColumnConfigBuilder sortable(boolean v0) {
        columnConfig.setSortable(v0);
        return this;
    }

    public ColumnConfigBuilder toolTip(com.google.gwt.safehtml.shared.SafeHtml v0) {
        columnConfig.setToolTip(v0);
        return this;
    }

    public ColumnConfigBuilder widget(com.google.gwt.user.client.ui.Widget v0, com.google.gwt.safehtml.shared.SafeHtml v1) {
        columnConfig.setWidget(v0, v1);
        return this;
    }

    public ColumnConfigBuilder width(int v0) {
        columnConfig.setWidth(v0);
        return this;
    }
}