package com.walmart.delivery.optimization.rest;

import business.Graph;
import com.walmart.delivery.optimization.beans.EntityMapFacade;
import com.walmart.delivery.optimization.entities.EntityLogisticsNetwork;
import com.walmart.delivery.optimization.entities.EntityMap;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author renannp
 */
@Path(value = "/")
public class DeliveryOptimizationServices implements Serializable
{
    @EJB
    EntityMapFacade entityMapEJB;    
    
    @PUT
    @Path(value = "/addLogisticsNetwork")
    @Produces(value = MediaType.APPLICATION_JSON)
    @Consumes(value = MediaType.APPLICATION_JSON)
    public EntityMap registerLogisticsNetwork(@Valid EntityMap newMapRequest)
    {
        // set the map object to the logistics network objects so they can reference the map during persist
        newMapRequest.getLogisticsNetwork().stream().forEach((n) -> {
            n.setEntityMap(newMapRequest);
        });
        
        // persist the new map in the database
        this.entityMapEJB.create(newMapRequest);

        return newMapRequest;
    }
}
