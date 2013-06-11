package biz.freshcode.learn.gwt.client.experiment.forms3;

public class Person {
    private String name;
    private String newPassword;

    @Override
    public String toString() {
        return "name: " + name + ", newPwdHash: " + hash(newPassword);
    }

    private int hash(String s) {
        if (s == null) return 0;
        else return s.hashCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
