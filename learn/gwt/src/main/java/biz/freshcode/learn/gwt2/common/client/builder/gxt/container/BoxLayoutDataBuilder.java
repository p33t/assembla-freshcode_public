package biz.freshcode.learn.gwt2.common.client.builder.gxt.container;

import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;

/**
 * Generated by BeanBuilderGenerator
 *
 * @see com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData
 */
@SuppressWarnings("UnusedDeclaration")
@biz.freshcode.learn.gwt2.common.client.builder.BeanBuilder(BoxLayoutData.class)
public class BoxLayoutDataBuilder extends biz.freshcode.learn.gwt2.common.client.builder.Construct.Parent<BoxLayoutDataBuilder> {
    public final BoxLayoutData boxLayoutData;

    public BoxLayoutDataBuilder(BoxLayoutData v) {
        boxLayoutData = v;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData#BoxLayoutData()
     */
    public BoxLayoutDataBuilder() {
        this(new BoxLayoutData());
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData#BoxLayoutData(com.sencha.gxt.core.client.util.Margins)
     */
    public BoxLayoutDataBuilder(com.sencha.gxt.core.client.util.Margins v0) {
        this(new BoxLayoutData(v0));
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData#setFlex(double)
     */
    public BoxLayoutDataBuilder flex(double v0) {
        boxLayoutData.setFlex(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData#setMargins(com.sencha.gxt.core.client.util.Margins)
     */
    public BoxLayoutDataBuilder margins(com.sencha.gxt.core.client.util.Margins v0) {
        boxLayoutData.setMargins(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData#setMaxSize(int)
     */
    public BoxLayoutDataBuilder maxSize(int v0) {
        boxLayoutData.setMaxSize(v0);
        return this;
    }

    /**
     * @see com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData#setMinSize(int)
     */
    public BoxLayoutDataBuilder minSize(int v0) {
        boxLayoutData.setMinSize(v0);
        return this;
    }
}
