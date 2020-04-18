package zzleep.core.settings;

public class DataConfig {

    private static String url;
    private static String user;
    private static String password;

    static {
        switch (System.getenv("_env")) {
            case "prod":
                configureProd();
                break;
            default:
                configureProd();
        }
    }

    private static void configureProd() {
        url = "jdbc:postgresql://ec2-176-34-97-213.eu-west-1.compute.amazonaws.com:5432/d2ka0f2unsn83u";
        user = "oyggsjqtgcdqlh";
        password = "c0b9d98e5e08a21f7c5915443f633865809943e6a1132ffcaa2454d1990ba6b6";
    }

    public static String getUrl() {
        return url;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }

}
