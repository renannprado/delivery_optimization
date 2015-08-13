package com.walmart.delivery.optimization.business;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author renannp
 */
public class Vertex {

    private final String name;
    private final List<Edge> edges;
    private Integer cost;
    private boolean visited;
    private Vertex shortestPathVertex;

    public Vertex(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
        this.visited = false;
        this.shortestPathVertex = null;
    }

    public String getName() {
        return name;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost, Vertex v) {
        this.cost = cost;
        this.shortestPathVertex = v;
    }

    public void addEdge(Vertex v, Integer distance) {
        this.edges.add(new Edge(v, distance));
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public boolean isVisited() {
        return visited;
    }

    public void visit() {
        this.visited = true;
    }

    public Vertex getShortestPathVertex() {
        return shortestPathVertex;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vertex other = (Vertex) obj;
        if (!this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vertex{" + "name=" + name + '}';
    }
}
