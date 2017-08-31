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

    public static void json(HttpServletResponse response, Object obj, ExclusionStrategy strategy) throws IOException {
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(strategy).create();
        response.getWriter().write(gson.toJson(obj));
    }

    public static void jsonError(HttpServletResponse response, String message, int code) throws IOException {
        response.getWriter().write(new Gson().toJson(new Error(code, message)));
    }

    static class Error {
        private final int code;
        private final String error;

        public Error(int code, String error) {
            this.code = code;
            this.error = error;
        }
    }
}
