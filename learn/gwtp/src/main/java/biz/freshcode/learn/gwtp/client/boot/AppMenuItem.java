package biz.freshcode.learn.gwtp.client.boot;

public class AppMenuItem {
    private final String text;
    private final String token;

    public AppMenuItem(String text, String token) {
        this.text = text;
        this.token = token;
    }

    public String getText() {
        return text;
    }

    public String getToken() {
        return token;
    }
}
