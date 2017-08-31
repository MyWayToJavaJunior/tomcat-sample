package servlet;

import config.ContextConfig;
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
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserDBService userDBService = new UserDBService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userDBService.get(request.getParameter("username"));
        if (user == null) {
            request.setAttribute("error", "Wrong username");
            ServletUtil.respond(request, response, "login");
        }
        else if (!User.PASSWORD_ENCODER.matches(request.getParameter("password"), user.getPassword())) {
            request.setAttribute("error", "Wrong password");
            ServletUtil.respond(request, response, "login");
        }

        request.getSession().setAttribute("principal", request.getParameter("username"));
        response.sendRedirect("/admin");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletUtil.respond(request, response, "login");
    }
}
