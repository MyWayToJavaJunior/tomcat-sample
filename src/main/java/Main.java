import rest.ArticleService;
import rest.AuthService;
import rest.filter.AuthFilter;
import rest.provider.GsonProvider;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class Main extends Application {
    private static final Set<Object> singletons  = new HashSet<Object>();

    public Main() {
        singletons.add(new ArticleService());
        singletons.add(new AuthService());
        singletons.add(new AuthFilter());
        singletons.add(new GsonProvider());
    }

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> set = new HashSet<Class<?>>();
        return set;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}