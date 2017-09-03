package dataset;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Data @Builder @AllArgsConstructor
@Entity @Table(name = "articles", indexes = @Index(name = "article_user", columnList = "user_id"))
public class Article {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Type(type = "text")
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default private Date createdAt = new Date();

    @ManyToOne @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    public Article() {
        this.createdAt = new Date();
    }

    public Article(String title, String text, User user) {
        this.title = title;
        this.text = text;
        this.user = user;
    }

    public static final ExclusionStrategy MIN_STRAT = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return f.getName().equals("user");
        }
        @Override
        public boolean shouldSkipClass(Class<?> aClass) {
            return false;
        }
    };
}
