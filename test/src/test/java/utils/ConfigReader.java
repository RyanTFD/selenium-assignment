package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            InputStream input = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("Config file not found!");
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public static String get(String key) {
    return properties.getProperty(key);
    }

    public static boolean getAsBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
