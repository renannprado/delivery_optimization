package business;

import com.walmart.delivery.optimization.entities.EntityLogisticsNetwork;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author renannp
 */
public class Graph 
{       
    // ConcurrentHashMap is faster than regular hashmap
    private final ConcurrentHashMap<String, Vertex> vertexList;
    
    public Graph() 
    {
        this.vertexList = new ConcurrentHashMap<>();
    }
    
    public void addVertex(Vertex v)
    {
        this.vertexList.put(v.getName(), v);
    }
    
    public void addVertices(ConcurrentHashMap<String, Vertex> vertices)
    {
        this.vertexList.putAll(vertices);
    }
    
    public static Graph buildGraphFromLogisticsNetwork(List<EntityLogisticsNetwork> network)
    {
        Graph g = new Graph();
        
        ConcurrentHashMap<String, Vertex> vertexHashMap = new ConcurrentHashMap<>();
        
        Vertex vFrom;
        Vertex vTo;
        
        for (EntityLogisticsNetwork e : network)
        {
            if ((vFrom = vertexHashMap.get(e.getSourceName())) == null)
            {
                vFrom = new Vertex(e.getSourceName());
                vertexHashMap.put(vFrom.getName(), vFrom);
            }
            
            if ((vTo = vertexHashMap.get(e.getDestinyName())) == null) 
            {
                vTo = new Vertex(e.getDestinyName());
                vertexHashMap.put(vTo.getName(), vTo);
            }
            
            vFrom.addEdge(vTo, e.getDistance());
        }
        
        g.addVertices(vertexHashMap);
        
        return g;
    }
    
    public void printGraph()
    {
        this.vertexList.values().stream().forEach((e) -> {
            System.out.println(e);
        });
    }
    
    /**
     * This method executes the Dijkstra's shortest path algorithm
     * @param vertexFromName the name of the vertex where the search should start
     * @param vertexToName the vertex that you want to get to 
     * @return if there`s a shortest path to <code>vertexTo</code>, it will return a stack with the shortest path,
     * and will return <b>empty</b> stack if there`s no shortest path to the <code>vertexTo</code> or if there`s no way to reach <code>vertexTo</code> 
     */
    public Stack<Vertex> shortestPathAlgorithm(String vertexFromName, String vertexToName)
    {
        Stack<Vertex> shortestPathStack = new Stack<>();
        
        Vertex current = null;
        List<Vertex> unvisitedList = new ArrayList<>();
        
        // set the the cost to "infinity" (Integer.MAX_VALUE in this case) 
        for (Vertex v : this.vertexList.values()) {
            if (!v.getName().equals(vertexFromName))
                v.setCost(Integer.MAX_VALUE, null);
            else
                current = v;
            
            unvisitedList.add(v);
        }
        
        // if the starting point wasn`t found, return empty graph
        if (current == null)
            return shortestPathStack;
        
        // and 0 to the current vertex
        current.setCost(0, null);
        
        boolean hasVisitedDestination = false;
        
        // all nodes are already marked as unvisited as that's set in the constructor
        
        // while there are vertices to be visited and destination wasn`t found and the current vertex's value isn`t infinity
        while (unvisitedList.size() > 0 && !hasVisitedDestination && current.getCost() < Integer.MAX_VALUE)
        {
            // calculate the cost of the neighbors (or vertices)
            for (Edge e : current.getEdges())
            {            
                // consider just the nodes marked as unvisited
                if (!e.getVertex().isVisited())
                {
                    final int cost = current.getCost() + e.getDistance();

                    if (cost < e.getVertex().getCost())
                        e.getVertex().setCost(cost, current);
                }
            }

            // remove the current vertex from the list of unvisited vertices
            unvisitedList.remove(current);
            // and mark it as visited
            current.visit();

            // if the current vertex is visited an is destination
            if (current.isVisited() && current.getName().equals(vertexToName))
                hasVisitedDestination = true;
            else if (unvisitedList.size() > 0)
                // get the vertex with smallest cost and chose it as the current vertex
                current = unvisitedList.parallelStream().min((v1, v2) -> Integer.compare(v1.getCost(), v2.getCost())).get();
        }
        
        // if has visited destination, add the vertices to the graph
        if (hasVisitedDestination)
        {
            // go backwars do get the path
            do
            {
                shortestPathStack.push(current);
                current = current.getShortestPathVertex();
            } while(current != null);
        }
        
        return shortestPathStack;
    }
}