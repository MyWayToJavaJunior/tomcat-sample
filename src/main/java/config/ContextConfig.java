package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ContextConfig {
    public static boolean DEV;
    public static boolean REST;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("/home/infatigabilis/Code/Education/Tomcat Sample/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        DEV = Boolean.parseBoolean(properties.getProperty("dev"));
        REST = Boolean.parseBoolean(properties.getProperty("rest"));
    }
}
