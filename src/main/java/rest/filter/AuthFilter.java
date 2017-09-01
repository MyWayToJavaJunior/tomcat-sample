package rest.filter;

import dataset.User;
import dbservice.UserDBService;

import javax.annotation.Priority;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;

@Provider
@PreMatching @Priority(1)
public class AuthFilter implements ContainerRequestFilter {
    private final UserDBService userDBService = new UserDBService();

    public void filter(ContainerRequestContext request) throws IOException {
        String token = request.getHeaderString("Token");
        if (token == null) return;

        User user = userDBService.getByToken(token);
        if (user == null) throw new BadRequestException();

        SecurityContext securityContext = new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return () -> user.getUsername();
            }

            @Override
            public boolean isUserInRole(String s) {
                return s.equals("USER");
            }

            @Override
            public boolean isSecure() {
                return true;
            }

            @Override
            public String getAuthenticationScheme() {
                return null;
            }
        };

        request.setSecurityContext(securityContext);
    }
}
