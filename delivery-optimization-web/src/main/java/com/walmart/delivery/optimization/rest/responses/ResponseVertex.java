package com.walmart.delivery.optimization.rest.responses;

import business.Vertex;

/**
 *
 * @author renannp
 */
public class ResponseVertex 
{
    private final String vertexName;
    
    public ResponseVertex(Vertex v)
    {
        this.vertexName = v.getName();
    }

    public String getVertexName() {
        return vertexName;
    }

    @Override
    public String toString() {
        return "ResponseVertex{" + "vertexName=" + vertexName + '}';
    }
}
