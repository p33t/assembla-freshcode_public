package biz.freshcode.learn.gwtp.shared.paginggrid;

import biz.freshcode.learn.gwtp.shared.generate.MetaGen;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import static biz.freshcode.learn.gwtp.client.util.ExceptionUtil.illegalState;

public class Tyre implements IsSerializable, MetaGen {
    public static final String DIAMETER = "diameter";
    public static final String THICKNESS = "thickness";

    private String id;
    private int diameter;
    private int thickness;

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public static interface Access extends PropertyAccess<Tyre> {
        ModelKeyProvider<Tyre> id();

        ValueProvider<Tyre, Integer> diameter();

        ValueProvider<Tyre, Integer> thickness();
    }

    public static class Comparator implements java.util.Comparator<Tyre> {
        private final String field;

        public Comparator(String field) {
            this.field = field;
        }

        @Override
        public int compare(Tyre o1, Tyre o2) {
            return getVal(o1) - getVal(o2); // dodgy b/c overflow
        }

        private int getVal(Tyre t) {
            if (Tyre.DIAMETER.equals(field)) return t.getDiameter();
            if (Tyre.THICKNESS.equals(field)) return t.getThickness();
            throw illegalState("Unknown field: " + field);
        }
    }
}
