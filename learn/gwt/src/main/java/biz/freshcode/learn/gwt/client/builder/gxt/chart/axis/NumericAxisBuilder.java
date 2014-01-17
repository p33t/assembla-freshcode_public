package biz.freshcode.learn.gwt.client.builder.gxt.chart.axis;

import biz.freshcode.learn.gwt.client.builder.BeanBuilder;
import biz.freshcode.learn.gwt.client.builder.Construct;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;

/**
 * Generated by BeanBuilderGenerator
 *
 * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis
 */
@BeanBuilder(NumericAxis.class)
@SuppressWarnings("UnusedDeclaration")
public class NumericAxisBuilder<M> extends Construct.Parent<NumericAxisBuilder<M>> {
    public final NumericAxis<M> numericAxis;

    public NumericAxisBuilder(NumericAxis<M> v) {
        numericAxis = v;
    }

    public NumericAxisBuilder() {
        this(new NumericAxis<M>());
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#addField(com.sencha.gxt.core.client.ValueProvider)
     */
    public NumericAxisBuilder<M> addField(com.sencha.gxt.core.client.ValueProvider<? super M, ? extends java.lang.Number> v0) {
        numericAxis.addField(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setAdjustMaximumByMajorUnit(boolean)
     */
    public NumericAxisBuilder<M> adjustMaximumByMajorUnit(boolean v0) {
        numericAxis.setAdjustMaximumByMajorUnit(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setAdjustMinimumByMajorUnit(boolean)
     */
    public NumericAxisBuilder<M> adjustMinimumByMajorUnit(boolean v0) {
        numericAxis.setAdjustMinimumByMajorUnit(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setAxisConfig(com.sencha.gxt.chart.client.draw.path.PathSprite)
     */
    public NumericAxisBuilder<M> axisConfig(com.sencha.gxt.chart.client.draw.path.PathSprite v0) {
        numericAxis.setAxisConfig(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setChart(com.sencha.gxt.chart.client.chart.Chart)
     */
    public NumericAxisBuilder<M> chart(com.sencha.gxt.chart.client.chart.Chart<M> v0) {
        numericAxis.setChart(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setDashSize(int)
     */
    public NumericAxisBuilder<M> dashSize(int v0) {
        numericAxis.setDashSize(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setDepth(double)
     */
    public NumericAxisBuilder<M> depth(double v0) {
        numericAxis.setDepth(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setDisplayGrid(boolean)
     */
    public NumericAxisBuilder<M> displayGrid(boolean v0) {
        numericAxis.setDisplayGrid(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setFields(java.util.List)
     */
    public NumericAxisBuilder<M> fields(java.util.List<com.sencha.gxt.core.client.ValueProvider<? super M, ? extends java.lang.Number>> v0) {
        numericAxis.setFields(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setGridDefaultConfig(com.sencha.gxt.chart.client.draw.path.PathSprite)
     */
    public NumericAxisBuilder<M> gridDefaultConfig(com.sencha.gxt.chart.client.draw.path.PathSprite v0) {
        numericAxis.setGridDefaultConfig(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setGridEvenConfig(com.sencha.gxt.chart.client.draw.path.PathSprite)
     */
    public NumericAxisBuilder<M> gridEvenConfig(com.sencha.gxt.chart.client.draw.path.PathSprite v0) {
        numericAxis.setGridEvenConfig(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setGridOddConfig(com.sencha.gxt.chart.client.draw.path.PathSprite)
     */
    public NumericAxisBuilder<M> gridOddConfig(com.sencha.gxt.chart.client.draw.path.PathSprite v0) {
        numericAxis.setGridOddConfig(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setHeight(double)
     */
    public NumericAxisBuilder<M> height(double v0) {
        numericAxis.setHeight(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setHidden(boolean)
     */
    public NumericAxisBuilder<M> hidden(boolean v0) {
        numericAxis.setHidden(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setInterval(double)
     */
    public NumericAxisBuilder<M> interval(double v0) {
        numericAxis.setInterval(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setLabelConfig(com.sencha.gxt.chart.client.draw.sprite.TextSprite)
     */
    public NumericAxisBuilder<M> labelConfig(com.sencha.gxt.chart.client.draw.sprite.TextSprite v0) {
        numericAxis.setLabelConfig(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setLabelOverlapHiding(boolean)
     */
    public NumericAxisBuilder<M> labelOverlapHiding(boolean v0) {
        numericAxis.setLabelOverlapHiding(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setLabelPadding(int)
     */
    public NumericAxisBuilder<M> labelPadding(int v0) {
        numericAxis.setLabelPadding(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setLabelProvider(com.sencha.gxt.data.shared.LabelProvider)
     */
    public NumericAxisBuilder<M> labelProvider(com.sencha.gxt.data.shared.LabelProvider<? super Number> v0) {
        numericAxis.setLabelProvider(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setLabelStepRatio(int)
     */
    public NumericAxisBuilder<M> labelStepRatio(int v0) {
        numericAxis.setLabelStepRatio(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setLabelTolerance(double)
     */
    public NumericAxisBuilder<M> labelTolerance(double v0) {
        numericAxis.setLabelTolerance(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setLength(double)
     */
    public NumericAxisBuilder<M> length(double v0) {
        numericAxis.setLength(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setMaximum(double)
     */
    public NumericAxisBuilder<M> maximum(double v0) {
        numericAxis.setMaximum(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setMinimum(double)
     */
    public NumericAxisBuilder<M> minimum(double v0) {
        numericAxis.setMinimum(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setMinorTickSteps(int)
     */
    public NumericAxisBuilder<M> minorTickSteps(int v0) {
        numericAxis.setMinorTickSteps(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setPosition(com.sencha.gxt.chart.client.chart.Chart.Position)
     */
    public NumericAxisBuilder<M> position(com.sencha.gxt.chart.client.chart.Chart.Position v0) {
        numericAxis.setPosition(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setSteps(int)
     */
    public NumericAxisBuilder<M> steps(int v0) {
        numericAxis.setSteps(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setTitleConfig(com.sencha.gxt.chart.client.draw.sprite.TextSprite)
     */
    public NumericAxisBuilder<M> titleConfig(com.sencha.gxt.chart.client.draw.sprite.TextSprite v0) {
        numericAxis.setTitleConfig(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setToolTipConfig(com.sencha.gxt.chart.client.chart.axis.AxisToolTipConfig)
     */
    public NumericAxisBuilder<M> toolTipConfig(com.sencha.gxt.chart.client.chart.axis.AxisToolTipConfig<M> v0) {
        numericAxis.setToolTipConfig(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setWidth(double)
     */
    public NumericAxisBuilder<M> width(double v0) {
        numericAxis.setWidth(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setX(double)
     */
    public NumericAxisBuilder<M> x(double v0) {
        numericAxis.setX(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.chart.client.chart.axis.NumericAxis#setY(double)
     */
    public NumericAxisBuilder<M> y(double v0) {
        numericAxis.setY(v0);
        return this;
    }
}
