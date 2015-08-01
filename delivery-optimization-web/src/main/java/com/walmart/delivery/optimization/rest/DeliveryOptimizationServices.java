package com.walmart.delivery.optimization.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author renannp
 */
@Path(value = "/")
@ApplicationScoped //make sure that all resources will be discarded after the call to these services
public class DeliveryOptimizationServices 
{    
    @GET
    @Path(value = "/addLogisticsNetwork")
    @Produces(value = MediaType.APPLICATION_JSON)
    @Consumes(value = MediaType.APPLICATION_JSON)
    public String registerLogisticsNetwork()
    {
        return "Success";
    }
}
