package pkg;

public class PropertyClient {
    public static void main(String[] args) {
        out("Hello");

        PropertyAndField.Host host = new PropertyAndField.Host();
        out(host);
        host.setName("sammy");
        out(host);
        host.setId(88);
        out(host);
        if (!host.getHasName()) throw new RuntimeException("Bad");
    }

    private static void out(Object arg) {
        System.out.println(arg);
    }
}
