package db.seeder;

import org.hibernate.Session;

public abstract class Seeder {
    protected final Session session;

    public Seeder(Session session) {
        this.session = session;
    }

    public abstract void run();
}
