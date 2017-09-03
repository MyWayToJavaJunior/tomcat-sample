package rest;

import dataset.Article;
import dataset.User;
import dbservice.ArticleDBService;
import dbservice.UserDBService;
import org.springframework.beans.factory.annotation.Autowired;
import rest.base.Secured;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.util.List;


@Path("/api/articles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArticleService extends RestService {
    @Autowired private ArticleDBService articleDBService;
    @Autowired private UserDBService userDBService;

    @GET @Path("{author}")
    public String get(@PathParam("author") String author) throws IOException {
        List<Article> result = author.equals("all") ? articleDBService.getAll() : articleDBService.getAllByAuthor(author);
        return respond(result, Article.MIN_STRAT);
    }

    @POST
    @Secured(User.Role.USER)
    public Response add(Article article, @Context SecurityContext ctx) {
        User user = userDBService.get(ctx.getUserPrincipal().getName()).get();
        article.setUser(user);

        articleDBService.save(article);
        return Response.ok().build();
    }

    @DELETE @Path("{id}")
    @Secured({User.Role.USER, User.Role.ADMIN})
    public Response destroy(@PathParam("id") long id, @Context SecurityContext ctx) {
        Article article = articleDBService.get(id).orElseThrow(() -> new NotFoundException());

        User user = userDBService.get(ctx.getUserPrincipal().getName()).get();
        if (!user.getRole().equals(User.Role.ADMIN) && !article.getUser().getUsername().equals(user.getUsername()))
            throw new ForbiddenException();

        articleDBService.delete(article);
        return Response.ok().build();
    }
}
