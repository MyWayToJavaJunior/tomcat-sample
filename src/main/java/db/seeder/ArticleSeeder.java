package db.seeder;

import dataset.Article;
import dataset.User;
import org.hibernate.Session;

import java.util.Date;

public class ArticleSeeder extends Seeder {
    public ArticleSeeder(Session session) {
        super(session);
    }

    @Override
    public void run() {
        session.save(Article.builder().title("Title1").text("Text1").user(session.get(User.class, 1L)).build());
        session.save(Article.builder().title("Title2").text("Text2").user(session.get(User.class, 2L)).build());
        session.save(Article.builder().title("Title3").text("Text3").user(session.get(User.class, 1L)).build());
        session.save(Article.builder().title("Title4").text("Text4").user(session.get(User.class, 1L)).build());
        session.save(Article.builder().title("Title5").text("Text5").user(session.get(User.class, 3L)).build());
        session.save(Article.builder().title("Title6").text("Text6").user(session.get(User.class, 2L)).build());
    }
}
