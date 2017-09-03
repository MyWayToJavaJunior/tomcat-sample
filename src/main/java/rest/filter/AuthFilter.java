package rest.filter;

import dataset.User;
import dbservice.UserDBService;
import rest.base.Secured;
import rest.exception.InvalidTokenException;
import rest.exception.WrongTokenException;

import javax.annotation.Priority;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.Path;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Provider @Secured
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {
    @Context
    private ResourceInfo resourceInfo;

    private final UserDBService userDBService = new UserDBService();

    public void filter(ContainerRequestContext requestContext) throws IOException {
        String token = getValidToken(requestContext.getHeaderString(HttpHeaders.AUTHORIZATION));
        User user = userDBService.getByToken(token).orElseThrow(() -> new WrongTokenException());
        checkRole(user);

        SecurityContext securityContext = new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return () -> user.getUsername();
            }

            @Override
            public boolean isUserInRole(String s) {
                return s.equals(user.getRole());
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

        requestContext.setSecurityContext(securityContext);
    }

    private String getValidToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.toLowerCase().startsWith("bearer "))
            throw new InvalidTokenException();

        return authorizationHeader.substring("Bearer".length()).trim();
    }

    private void checkRole(User user) {
        Class<?> resourceClass = resourceInfo.getResourceClass();
        List<User.Role> classRoles = extractRoles(resourceClass);

        Method resourceMethod = resourceInfo.getResourceMethod();
        List<User.Role> methodRoles = extractRoles(resourceMethod);

        if (methodRoles.isEmpty()) {
            checkPermissions(classRoles, user);
        } else {
            checkPermissions(methodRoles, user);
        }
    }

    private List<User.Role> extractRoles(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return new ArrayList<User.Role>();
        } else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return new ArrayList<User.Role>();
            } else {
                User.Role[] allowedRoles = secured.value();
                return Arrays.asList(allowedRoles);
            }
        }
    }

    private void checkPermissions(List<User.Role> roles, User user) {
        for (User.Role role : roles)
            if (role.equals(user.getRole())) return;

        throw new ForbiddenException();
    }
}
