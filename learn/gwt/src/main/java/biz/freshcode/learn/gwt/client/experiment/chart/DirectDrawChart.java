package biz.freshcode.learn.gwt.client.experiment.chart;

import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt.client.util.IdentityHashProvider;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.chart.ChartBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.chart.axis.NumericAxisBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.chart.series.LineSeriesBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.draw.path.PathSpriteBuilder;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.series.Primitives;
import com.sencha.gxt.chart.client.draw.path.PathSprite;
import com.sencha.gxt.chart.client.draw.sprite.Sprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.data.shared.ListStore;

import java.util.List;
import java.util.ListIterator;

import static biz.freshcode.learn.gwt.client.experiment.chart.PointAccess.ACCESS;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newList;
import static com.sencha.gxt.chart.client.chart.Chart.Position;

public class DirectDrawChart extends AbstractIsWidget<Chart<Point>> {
    private final List<Sprite> sprites = newList();

    public void display(Point... points) {
        removeAll();
        Chart<Point> c = asWidget();

        for (Point p : points) {
            PathSprite s = new PathSpriteBuilder(Primitives.cross(0, 0, 2))
                    .translation(p.getX(), p.getY())
                    .pathSprite;
            sprites.add(s);
            c.addSprite(s);
        }

        c.redrawChart();
        // Don't seem to need this
//        c.redrawSurface();
    }

    private void removeAll() {
        for (ListIterator<Sprite> it = sprites.listIterator(); it.hasNext(); ) {
            Sprite next = it.next();
            it.remove();
            asWidget().remove(next);
        }
    }

    @Override
    protected Chart<Point> createWidget() {
        ListStore<Point> store = new ListStore<Point>(new IdentityHashProvider<Point>());
        store.add(new Point(15, 15));

        return new ChartBuilder<Point>()
                .store(store)
                .addAxis(new NumericAxisBuilder<Point>()
                        .position(Position.BOTTOM)
                        .titleConfig(new TextSprite("Some Axis"))
                        .addField(ACCESS.x())
                        .minimum(0)
                        .maximum(100)
                        .steps(10)
                        .numericAxis)
                .addAxis(new NumericAxisBuilder<Point>()
                        .position(Position.LEFT)
                        .addField(ACCESS.y())
                        .minimum(0)
                        .maximum(100)
                        .steps(5)
                        .numericAxis)
                .addSeries(new LineSeriesBuilder<Point>()
                        .xField(ACCESS.x())
                        .yField(ACCESS.y())
                        .lineSeries)
                .chart;
    }
}
