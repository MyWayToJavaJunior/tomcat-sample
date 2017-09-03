import org.springframework.context.annotation.Configuration;
import rest.ArticleService;
import rest.AuthService;
import rest.base.Secured;
import rest.exception.InvalidTokenMapper;
import rest.exception.WrongTokenException;
import rest.exception.WrongTokenMapper;
import rest.filter.AuthFilter;
import rest.provider.GsonProvider;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class Main extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> set = new HashSet<Class<?>>();
        set.add(Secured.class);
        set.add(InvalidTokenMapper.class);
        set.add(WrongTokenMapper.class);
        set.add(ArticleService.class);
        set.add(AuthService.class);
        return set;
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons  = new HashSet<>();
        singletons.add(new AuthFilter());
        singletons.add(new GsonProvider());
        return singletons;
    }
}