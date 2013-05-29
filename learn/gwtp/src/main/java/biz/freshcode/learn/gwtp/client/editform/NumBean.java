package biz.freshcode.learn.gwtp.client.editform;

import com.google.gwt.user.client.rpc.IsSerializable;

public class NumBean implements IsSerializable {
    private int numLeft;
    private int numRight;

    public int getNumLeft() {
        return numLeft;
    }

    public void setNumLeft(int numLeft) {
        this.numLeft = numLeft;
    }

    public int getNumRight() {
        return numRight;
    }

    public void setNumRight(int numRight) {
        this.numRight = numRight;
    }
}
