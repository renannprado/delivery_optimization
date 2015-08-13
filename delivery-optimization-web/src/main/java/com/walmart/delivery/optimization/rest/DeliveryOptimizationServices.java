package com.walmart.delivery.optimization.rest;

import com.walmart.delivery.optimization.beans.EntityMapFacade;
import com.walmart.delivery.optimization.business.Graph;
import com.walmart.delivery.optimization.business.Vertex;
import com.walmart.delivery.optimization.entities.EntityLogisticsNetwork;
import com.walmart.delivery.optimization.entities.EntityMap;
import com.walmart.delivery.optimization.rest.responses.ResponseVertex;
import com.walmart.delivery.optimization.rest.responses.ShortestPathResponse;
import java.io.Serializable;
import java.util.List;
import java.util.Stack;
import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    
    /**
     * Registers a new map
     * @param newMapRequest
     * @return 
     */
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
    
    /**
     * This webservice executes the shortest path algorithm for a given <code>mapName</code>,<code>from</code> and <code>to</code>
     * After finding the shortest path, it calculates how much gas would it take to drive through this path and return the total cost of the travel
     * @param mapName
     * @param from
     * @param to
     * @param autonomy
     * @param gasPrice
     * @return 
     */
    @GET
    @Path(value = "/getShortestPath")
    @Produces(value = MediaType.APPLICATION_JSON)
    public ShortestPathResponse getShortestPath(@QueryParam(value = "mapName") String mapName,
                                @QueryParam(value = "from") String from,
                                @QueryParam(value = "to") String to,
                                @QueryParam(value = "autonomy") float autonomy,
                                @QueryParam(value = "gasPrice") float gasPrice)
    {
        // search for the network paths based on the map name
        List<EntityLogisticsNetwork> networkByMapName = entityMapEJB.getNetworkByMapName(mapName);
        
        // build the graph based on the network 
        Graph graph = Graph.buildGraphFromLogisticsNetwork(networkByMapName);
        
        // run Dijkstra algorithm 
        Stack<Vertex> shortestPathStack = graph.shortestPathAlgorithm(from.toUpperCase(), to.toUpperCase());
        
        // returns null if the algorithm wasn`t able to determine a shortest path 
        if (shortestPathStack.size() == 0)
            return null;
        else
        {
            // send information about the shortest path and cost to the caller
            Stack<ResponseVertex> newStack = new Stack<>();
            
            float distance = 0;
            
            while(shortestPathStack.size() > 0)
            {
                Vertex v = shortestPathStack.pop();
                
                newStack.push(new ResponseVertex(v));
            
                // the last one in the stack will hold the total distance
                if (shortestPathStack.size() == 0)
                    distance = v.getCost();
            }
            
            float totalGasCost = (distance * gasPrice) / autonomy;
            
            return new ShortestPathResponse(newStack, totalGasCost);
        }
    }
    
    /**
     * 
     * @return all the maps of the database
     */
    @GET
    @Path(value = "/getAllMaps")
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<EntityMap> getAllMaps() {
        return entityMapEJB.findAll();
    }
}
