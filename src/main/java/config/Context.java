package config;

import config.BeanConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Context {
    private static ApplicationContext applicationContext;

    private Context() {}

    public static ApplicationContext instance() {
        return applicationContext != null ? applicationContext : new AnnotationConfigApplicationContext(BeanConfig.class);
    }
}
