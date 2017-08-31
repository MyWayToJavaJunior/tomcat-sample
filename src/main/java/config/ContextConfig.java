package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ContextConfig {
    public static boolean DEV;

    static {
        Properties properties = new Properties();
        try {
            // TODO: fix path
            properties.load(new FileInputStream("/home/infatigabilis/Code/Education/Tomcat Sample/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        DEV = Boolean.parseBoolean(properties.getProperty("dev"));
    }
}
