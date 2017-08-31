package servlet;

import dataset.User;
import dbservice.UserDBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by infatigabilis on 30.08.17.
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/api/login")
public class LoginServlet extends HttpServlet {
    private UserDBService userDBService = new UserDBService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userDBService.get(request.getParameter("username"));
        if (user == null) {
            ServletUtil.respondError(response, "Wrong username", 400);
        }
        else if (!User.PASSWORD_ENCODER.matches(request.getParameter("password"), user.getPassword())) {
            ServletUtil.respondError(response, "Wrong password", 400);
        }

        user.generateToken();
        userDBService.save(user);
        ServletUtil.respond(response, new Token(user.getUsername(), user.getToken()), null);
    }

    class Token {
        private String username;
        private String token;

        public Token(String username, String token) {
            this.username = username;
            this.token = token;
        }
    }
}
