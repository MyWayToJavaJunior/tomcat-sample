package servlet;

import config.ContextConfig;
import dataset.Article;
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
@WebServlet(name = "AdminServlet", urlPatterns = "/admin/*")
public class AdminServlet extends HttpServlet {
    private final ArticleDBService articleDBService = new ArticleDBService();
    private final UserDBService userDBService = new UserDBService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("delete".equals(request.getParameter("method"))) {
            doDelete(request, response);
            return;
        }

        Article newArticle = new Article(request.getParameter("title"), request.getParameter("text"),
                userDBService.get(request.getSession().getAttribute("principal").toString()));

        articleDBService.save(newArticle);
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("articles", articleDBService.getAllByAuthor(request.getSession().getAttribute("principal").toString()));
        ServletUtil.respond(request, response, "admin");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Article article = articleDBService.get(id);

        if (article == null) {
            resp.sendError(404);
            return;
        }
        else if (!article.getUser().getUsername().equals(req.getSession().getAttribute("principal").toString())) {
            resp.sendRedirect("/login");
            return;
        }

        articleDBService.delete(article);
        doGet(req, resp);
    }
}
