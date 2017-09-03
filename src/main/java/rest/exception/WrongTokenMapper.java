package rest.exception;

import rest.filter.AuthFilter;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WrongTokenMapper
        implements ExceptionMapper<WrongTokenException> {

    public Response toResponse(WrongTokenException e) {
        return Response.accepted("Wrong token").status(Response.Status.BAD_REQUEST).build();
    }
}
