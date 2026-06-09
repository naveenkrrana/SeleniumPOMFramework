package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    // Static block loads the properties file into memory once when execution starts
    static {
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(file);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException("Could not find the config.properties file!");
        }
    }

    // Fetch config values (like URL or credentials) from anywhere in the framework
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}