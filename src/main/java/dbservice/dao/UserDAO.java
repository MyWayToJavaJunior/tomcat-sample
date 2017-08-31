package dbservice.dao;

import dataset.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserDAO extends DAO {
    public UserDAO(Session session) {
        super(session);
    }

    public void save(User userDataSet) {
        session.save(userDataSet);
    }

    public User get(long id) {
        return session.load(User.class, id);
    }

    public User get(String username) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> from = criteria.from(User.class);
        criteria.where(builder.equal(from.get("username"), username));
        Query<User> query = session.createQuery(criteria);
        return query.uniqueResult();
    }
}
