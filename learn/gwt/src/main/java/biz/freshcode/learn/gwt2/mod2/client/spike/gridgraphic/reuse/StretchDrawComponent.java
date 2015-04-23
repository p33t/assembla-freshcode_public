package biz.freshcode.learn.gwt2.mod2.client.spike.gridgraphic.reuse;

import com.sencha.gxt.chart.client.draw.DrawComponent;
import com.sencha.gxt.chart.client.draw.Scaling;
import com.sencha.gxt.chart.client.draw.sprite.Sprite;

/**
 * Stretches sprites by interpreting their original dimensions as proportions of the width/height.
 * This uses {@link Sprite#setScaling(Scaling)}.
 */
public class StretchDrawComponent extends DrawComponent {
    public StretchDrawComponent() {
        super(1, 1);
    }

    @Override
    protected void onResize(int width, int height) {
        Scaling scaling = new Scaling(width, height);
        for (Sprite s : surface.getSprites()) {
            s.setScaling(scaling);
        }
        super.onResize(width, height);
    }
}
