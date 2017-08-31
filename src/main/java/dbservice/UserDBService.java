package dbservice;

import dataset.User;
import db.HibernateUtil;
import dbservice.dao.UserDAO;
import org.hibernate.Session;

public class UserDBService {
    public void save(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            new UserDAO(session).save(user);
        }
    }

    public User get(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return new UserDAO(session).get(id);
        }
    }

    public User get(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return new UserDAO(session).get(username);
        }
    }
}
