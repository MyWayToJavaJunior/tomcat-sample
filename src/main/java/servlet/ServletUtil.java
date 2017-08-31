package servlet;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataset.Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletUtil {
    private static final String JSP_DIR = "/WEB-INF/jsp/";

    public static void respond(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        request.getRequestDispatcher(JSP_DIR + page + ".jsp").forward(request, response);
    }
}
