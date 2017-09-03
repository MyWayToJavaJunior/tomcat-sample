package rest.exception;

import rest.filter.AuthFilter;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidTokenMapper
        implements ExceptionMapper<InvalidTokenException> {

    public Response toResponse(InvalidTokenException e) {
        return Response.accepted("Invalid token").status(Response.Status.BAD_REQUEST).build();
    }
}
