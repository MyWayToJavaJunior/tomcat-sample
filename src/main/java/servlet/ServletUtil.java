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
    public static void respond(HttpServletResponse response, Object obj, ExclusionStrategy strategy) throws IOException {
        Gson gson = strategy != null ? new GsonBuilder().addSerializationExclusionStrategy(strategy).create() : new Gson();
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(obj));
    }

    public static void respondError(HttpServletResponse response, String message, int code) throws IOException {
        response.setStatus(code);
        response.setContentType("application/json");
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
