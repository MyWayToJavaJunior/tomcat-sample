package db;

import config.ContextConfig;
import dataset.Article;
import dataset.User;
import db.seeder.ArticleSeeder;
import db.seeder.UserSeeder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Article.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/test");
        configuration.setProperty("hibernate.connection.username", "homestead");
        configuration.setProperty("hibernate.connection.password", "secret");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", ContextConfig.DEV ? "create" : "none");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        if (ContextConfig.DEV) seed();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static void seed() {
        try (Session session = sessionFactory.openSession()) {
            new UserSeeder(session).run();
            new ArticleSeeder(session).run();
        }
    }
}
