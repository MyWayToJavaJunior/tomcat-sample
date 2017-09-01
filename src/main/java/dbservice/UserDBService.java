package dbservice;

import dataset.User;
import db.HibernateUtil;
import dbservice.dao.UserDAO;
import org.hibernate.Session;

import java.util.Optional;

public class UserDBService {
    public void save(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            new UserDAO(session).save(user);
            session.flush();
        }
    }

    public User get(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return new UserDAO(session).get(id);
        }
    }

    public Optional<User> get(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.of(new UserDAO(session).get(username));
        }
    }

    public User getByToken(String token) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return new UserDAO(session).getByToken(token);
        }
    }
}
