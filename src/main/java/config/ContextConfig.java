package config;

import dbservice.ArticleDBService;
import dbservice.UserDBService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class ContextConfig {
    public static boolean DEV;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(ContextConfig.class.getClassLoader().getResource("config.properties").toURI())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        DEV = Boolean.parseBoolean(properties.getProperty("dev"));
    }
}
