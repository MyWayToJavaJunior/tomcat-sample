package rest;

import dataset.User;
import dbservice.UserDBService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.io.IOException;


@Path("/api/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthService extends RestService {
    @Autowired private UserDBService userDBService;

    @POST @Path("login")
    public String login(Creditional creditional) throws IOException {
        User user = userDBService.get(creditional.username).orElseThrow(() -> new NotFoundException());
        if (!User.PASSWORD_ENCODER.matches(creditional.password, user.getPassword())) throw new BadRequestException();

        user.generateToken();
        userDBService.save(user);

        return respond(new Token(user.getUsername(), user.getToken()), null);
    }

    public class Creditional {
        String username;
        String password;

        public Creditional() {
        }
    }

    public class Token {
        private String username;
        private String token;

        public Token() {
        }

        public Token(String username, String token) {
            this.username = username;
            this.token = token;
        }
    }
}
