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
        session.save(User.builder().username("user1").password(User.PASSWORD_ENCODER.encode("pass1")).build());
        session.save(User.builder().username("user2").password(User.PASSWORD_ENCODER.encode("pass2")).build());
        session.save(User.builder().username("user3").password(User.PASSWORD_ENCODER.encode("pass3")).build());
    }
}
