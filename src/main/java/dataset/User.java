package dataset;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "users")
public class User {
    public static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Builder.Default private String token = UUID.randomUUID().toString();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default private List<Article> articles = new ArrayList<>();

    public void generateToken() {
        this.token = UUID.randomUUID().toString();
    }
}
