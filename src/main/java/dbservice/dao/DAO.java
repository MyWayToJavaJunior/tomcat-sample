package dbservice.dao;

import org.hibernate.Session;

public class DAO {
    protected final Session session;

    public DAO(Session session) {
        this.session = session;
    }
}
