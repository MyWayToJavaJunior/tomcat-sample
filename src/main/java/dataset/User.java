package dataset;

import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "users")
public class User {
    public static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull @Column(unique = true, nullable = false)
    private String username;

    @NonNull @Column(nullable = false)
    private String password;

    @NonNull @Enumerated(EnumType.STRING)
    private Role role;

    private String token;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Article> articles = new ArrayList<>();

    public void generateToken() {
        this.token = UUID.randomUUID().toString();
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public enum Role {
        ADMIN, USER
    }
}
