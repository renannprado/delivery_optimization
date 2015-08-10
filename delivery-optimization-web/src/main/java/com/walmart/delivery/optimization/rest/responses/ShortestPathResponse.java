package com.walmart.delivery.optimization.rest.responses;

import java.util.Stack;

/**
 *
 * @author renannp
 */
public class ShortestPathResponse {
    
    private Stack<ResponseVertex> shortestPath;
    private float totalCost;
    
    public ShortestPathResponse(Stack<ResponseVertex> shortestPath, float totalCost) 
    {
        this.shortestPath = shortestPath;
        this.totalCost = totalCost;
    }

    public Stack<ResponseVertex> getShortestPath() {
        return shortestPath;
    }

    public float getTotalCost() {
        return totalCost;
    }
}
