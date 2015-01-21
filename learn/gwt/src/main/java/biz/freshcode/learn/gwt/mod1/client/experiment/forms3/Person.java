package biz.freshcode.learn.gwt.mod1.client.experiment.forms3;

public class Person {
    private String name;
    private String newPassword;
    private Flavour favourite;

    @Override
    public String toString() {
        return "name: " + name + ", newPwdHash: " + hash(newPassword) + ", fav: " + favourite;
    }

    private int hash(String s) {
        if (s == null) return 0;
        else return s.hashCode();
    }

    public Flavour getFavourite() {
        return favourite;
    }

    public void setFavourite(Flavour favourite) {
        this.favourite = favourite;
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
