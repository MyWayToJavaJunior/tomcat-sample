package rest;

import dataset.Article;
import dataset.User;
import dbservice.ArticleDBService;
import dbservice.UserDBService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.util.List;

import static rest.RestUtil.respond;

@Path("/api/articles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArticleService {
    private final ArticleDBService articleDBService = new ArticleDBService();
    private final UserDBService userDBService = new UserDBService();

    @GET @Path("{author}")
    public String get(@PathParam("author") String author) throws IOException {
        List<Article> result = author.equals("all") ? articleDBService.getAll() : articleDBService.getAllByAuthor(author);
        return respond(result, Article.MIN_STRAT);
    }

    @POST
    @RolesAllowed("USER")
    public Response add(Article article, @Context SecurityContext ctx) {
        User user = userDBService.get(ctx.getUserPrincipal().getName()).get();
        article.setUser(user);

        articleDBService.save(article);
        return Response.ok().build();
    }

    @DELETE @Path("{id}")
    @RolesAllowed("USER")
    public Response destroy(@PathParam("id") long id, @Context SecurityContext ctx) {
        Article article = articleDBService.get(id).orElseThrow(() -> new NotFoundException());
        if (!article.getUser().getUsername().equals(ctx.getUserPrincipal().getName())) throw new ForbiddenException();

        articleDBService.delete(article);
        return Response.ok().build();
    }
}
