package com.walmart.delivery.optimization.rest;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author renannp
 */
@ApplicationPath(value = "/rest")
public class ApplicationConfig extends Application
{
    @Override
    public Set<Class<?>> getClasses() 
    {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    
    /**
     * Here we add the classes that are responsible to handle the RESTful webservices
     */
    private void addRestResourceClasses(Set<Class<?>> resources) 
    {
        resources.add(com.walmart.delivery.optimization.config.JacksonConfig.class);
        resources.add(com.walmart.delivery.optimization.rest.DeliveryOptimizationServices.class);
    }
}
