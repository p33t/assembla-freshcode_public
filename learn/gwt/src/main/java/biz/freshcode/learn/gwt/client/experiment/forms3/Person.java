package biz.freshcode.learn.gwt.client.experiment.forms3;

import java.util.Set;

public class Person {
    private String newPassword;
    private Set<Flavour> likes;

    @Override
    public String toString() {
        return "likes: " + likes + ", newPwdHash: " + hash(newPassword);
    }

    private int hash(String s) {
        if (s == null) return 0;
        else return s.hashCode();
    }

    public Set<Flavour> getLikes() {
        return likes;
    }

    public void setLikes(Set<Flavour> likes) {
        this.likes = likes;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
