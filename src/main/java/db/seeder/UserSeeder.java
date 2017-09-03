package db.seeder;

import dataset.Article;
import dataset.User;
import org.hibernate.Session;

public class UserSeeder extends Seeder {
    public UserSeeder(Session session) {
        super(session);
    }

    @Override
    public void run() {
        session.save(new User("admin", User.PASSWORD_ENCODER.encode("secret"), User.Role.ADMIN));
        session.save(new User("user1", User.PASSWORD_ENCODER.encode("pass1"), User.Role.USER));
        session.save(new User("user2", User.PASSWORD_ENCODER.encode("pass2"), User.Role.USER));
        session.save(new User("user3", User.PASSWORD_ENCODER.encode("pass3"), User.Role.USER));
    }
}
