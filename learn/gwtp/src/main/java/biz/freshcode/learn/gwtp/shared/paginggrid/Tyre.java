package biz.freshcode.learn.gwtp.shared.paginggrid;

import com.google.gwt.user.client.rpc.IsSerializable;

import static biz.freshcode.learn.gwtp.util.shared.ExceptionUtil.illegalState;

public class Tyre implements IsSerializable {
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

    public static class Comparator implements java.util.Comparator<Tyre> {
        private final String field;
        private final boolean ascending;

        public Comparator(String field, boolean ascending) {
            this.field = field;
            this.ascending = ascending;
        }

        @Override
        public int compare(Tyre o1, Tyre o2) {
            int asc = getVal(o1) - getVal(o2);
            return ascending? asc: -asc; // dodgy b/c overflow
        }

        private int getVal(Tyre t) {
            if (DIAMETER.equals(field)) return t.getDiameter();
            if (THICKNESS.equals(field)) return t.getThickness();
            throw illegalState("Unknown field: " + field);
        }
    }
}
