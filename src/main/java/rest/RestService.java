package rest;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.Context;

import java.io.IOException;

public class RestService {
    public RestService() {
        Context.instance().getAutowireCapableBeanFactory().autowireBean(this);
    }

    protected String respond(Object obj, ExclusionStrategy strategy) throws IOException {
        Gson gson = strategy != null ? new GsonBuilder().addSerializationExclusionStrategy(strategy).create() : new Gson();
        return gson.toJson(obj);
    }
}
