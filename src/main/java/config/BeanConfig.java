package config;

import dbservice.ArticleDBService;
import dbservice.UserDBService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Configuration
public class BeanConfig {

    @Bean
    public ArticleDBService articleDBService() {
        return new ArticleDBService();
    }

    @Bean
    public UserDBService userDBService() {
        return new UserDBService();
    }
}
