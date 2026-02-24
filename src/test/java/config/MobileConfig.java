package config;

import java.io.InputStream;
import java.util.Properties;

public class MobileConfig {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = MobileConfig.class
                .getClassLoader()
                .getResourceAsStream("mobile.properties")) {

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Cannot load mobile.properties", e);
        }
    }

    private static String get(String key) {
        return System.getProperty(key,
                properties.getProperty(key));
    }

    public static String appiumUrl() {
        return get("appium.url");
    }

    public static String deviceName() {
        return get("device.name");
    }

    public static String platformVersion() {
        return get("platform.version");
    }

}

