package servlet;

import dataset.Article;
import dataset.User;
import dbservice.ArticleDBService;
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
@WebServlet(name = "AdminServlet", urlPatterns = "/api/admin/*")
public class AdminServlet extends HttpServlet {
    private final ArticleDBService articleDBService = new ArticleDBService();
    private final UserDBService userDBService = new UserDBService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userDBService.getByToken(request.getHeader("Token"));
        if (user == null) {
            ServletUtil.respondError(response, "Invalid token", 401);
            return;
        }

        Article newArticle = new Article(request.getParameter("title"), request.getParameter("text"), user);

        articleDBService.save(newArticle);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Article article = articleDBService.get(id);

        if (article == null) {
            ServletUtil.respondError(resp, "Object not found in DB", 404);
            return;
        }
        else if (!article.getUser().getToken().equals(req.getHeader("Token"))) {
            ServletUtil.respondError(resp, "Forbidden", 403);
            return;
        }

        articleDBService.delete(article);
    }
}
