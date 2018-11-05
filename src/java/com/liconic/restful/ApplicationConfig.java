package com.liconic.restful;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.liconic.restful.CancelJob.class);
        resources.add(com.liconic.restful.CompletedJob.class);
        resources.add(com.liconic.restful.DoneJob.class);
        resources.add(com.liconic.restful.FailedJob.class);
        resources.add(com.liconic.restful.GenericResource.class);
        resources.add(com.liconic.restful.ImportBuffer.class);
    }

}
