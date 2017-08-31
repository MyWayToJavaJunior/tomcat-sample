package dbservice;

import dataset.Article;
import db.HibernateUtil;
import dbservice.dao.ArticleDAO;
import dbservice.dao.UserDAO;
import org.hibernate.Session;

import java.util.List;

public class ArticleDBService {
    public void save(Article article) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            new ArticleDAO(session).save(article);
        }
    }

    public Article get(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return new ArticleDAO(session).get(id);
        }
    }

    public List<Article> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return new ArticleDAO(session).getAll();
        }
    }

    public List<Article> getAllByAuthor(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return new UserDAO(session).get(username).getArticles();
        }
    }

    public void delete(Article obj) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            new ArticleDAO(session).delete(obj);
        }
    }
}
