package servlet.filter;

import servlet.ServletUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by infatigabilis on 30.08.17.
 */
@WebFilter(filterName = "AdminFilter", urlPatterns = "/api/admin/*")
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if (((HttpServletRequest) req).getHeader("Token") == null) {
            ServletUtil.respondError((HttpServletResponse) resp, "Token required", 401);
            return;
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
