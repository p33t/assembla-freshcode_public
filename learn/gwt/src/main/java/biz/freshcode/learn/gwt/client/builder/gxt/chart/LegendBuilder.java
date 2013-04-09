package biz.freshcode.learn.gwt.client.builder.gxt.chart;

import com.sencha.gxt.chart.client.chart.Legend;

/**
 * Generated by BeanBuilderGenerator
 *
 * @see com.sencha.gxt.chart.client.chart.Legend
 */
@biz.freshcode.learn.gwt.client.uispike.builder.BeanBuilder(Legend.class)
public class LegendBuilder<M> extends biz.freshcode.learn.gwt.client.uispike.builder.Construct.Parent<LegendBuilder<M>> {
    public final Legend<M> legend;

    public LegendBuilder(Legend<M> v) {
        legend = v;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.Legend#Legend()
     */
    public LegendBuilder() {
        this(new Legend<M>());
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.Legend#setBorderConfig(com.sencha.gxt.chart.client.draw.sprite.RectangleSprite)
     */
    public LegendBuilder<M> borderConfig(com.sencha.gxt.chart.client.draw.sprite.RectangleSprite v0) {
        legend.setBorderConfig(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.Legend#setChart(com.sencha.gxt.chart.client.chart.Chart)
     */
    public LegendBuilder<M> chart(com.sencha.gxt.chart.client.chart.Chart<M> v0) {
        legend.setChart(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.Legend#setItemHiding(boolean)
     */
    public LegendBuilder<M> itemHiding(boolean v0) {
        legend.setItemHiding(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.Legend#setItemHighlighting(boolean)
     */
    public LegendBuilder<M> itemHighlighting(boolean v0) {
        legend.setItemHighlighting(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.Legend#setLabelRenderer(com.sencha.gxt.chart.client.chart.series.SeriesRenderer)
     */
    public LegendBuilder<M> labelRenderer(com.sencha.gxt.chart.client.chart.series.SeriesRenderer<M> v0) {
        legend.setLabelRenderer(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.Legend#setLineRenderer(com.sencha.gxt.chart.client.chart.series.SeriesRenderer)
     */
    public LegendBuilder<M> lineRenderer(com.sencha.gxt.chart.client.chart.series.SeriesRenderer<M> v0) {
        legend.setLineRenderer(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.Legend#setMarkerRenderer(com.sencha.gxt.chart.client.chart.series.SeriesRenderer)
     */
    public LegendBuilder<M> markerRenderer(com.sencha.gxt.chart.client.chart.series.SeriesRenderer<M> v0) {
        legend.setMarkerRenderer(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.Legend#setPadding(double)
     */
    public LegendBuilder<M> padding(double v0) {
        legend.setPadding(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.Legend#setPosition(com.sencha.gxt.chart.client.chart.Chart.Position)
     */
    public LegendBuilder<M> position(com.sencha.gxt.chart.client.chart.Chart.Position v0) {
        legend.setPosition(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.Legend#setToolTipConfig(com.sencha.gxt.chart.client.chart.LegendToolTipConfig)
     */
    public LegendBuilder<M> toolTipConfig(com.sencha.gxt.chart.client.chart.LegendToolTipConfig<M> v0) {
        legend.setToolTipConfig(v0);
        return this;
    }
}
