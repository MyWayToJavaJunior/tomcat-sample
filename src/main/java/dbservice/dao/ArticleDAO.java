package dbservice.dao;

import dataset.Article;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ArticleDAO extends DAO {
    public ArticleDAO(Session session) {
        super(session);
    }

    public void save(Article article) {
        session.save(article);
    }

    public Article get(long id) {
        return session.get(Article.class, id);
    }

    public List<Article> getAll() {
        CriteriaQuery<Article> criteria = session.getCriteriaBuilder().createQuery(Article.class);
        criteria.from(Article.class);
        return session.createQuery(criteria).getResultList();
    }

    public void delete(Article obj) {
        session.beginTransaction();
        session.delete(obj);
        session.flush();
    }
}
