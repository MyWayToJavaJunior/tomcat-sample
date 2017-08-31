package servlet;

import config.ContextConfig;
import dataset.Article;
import dbservice.ArticleDBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by infatigabilis on 30.08.17.
 */
@WebServlet(name = "ArticleServlet", urlPatterns = "/articles")
public class ArticleServlet extends HttpServlet {
    private ArticleDBService articleDBService = new ArticleDBService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String author = request.getParameter("author");
        List<Article> result = author == null ? articleDBService.getAll() : articleDBService.getAllByAuthor(author);

        if (ContextConfig.REST) ServletUtil.json(response, result, Article.GSON_STRAT_1);
        else {
            request.setAttribute("data", result);
            request.setAttribute("title", author == null ? "All articles" : "Articles by " + author);
            ServletUtil.respond(request, response, "article");
        }
    }
}
