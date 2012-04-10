package biz.freshcode.learn.gwt.client.uispike.gxt;

public class Row {
    private String str;
    private Integer num;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "(str:" + str + "|num:" + num + ")";
    }
}
