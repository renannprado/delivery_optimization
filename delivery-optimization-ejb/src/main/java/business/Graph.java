package business;

import com.walmart.delivery.optimization.entities.EntityLogisticsNetwork;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author renannp
 */
public class Graph 
{   
    public static class Vertex
    {
        private final String name;

        public Vertex(String name)
        {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
        
        @Override
        public int hashCode() {
            int hash = 3;
            hash = 53 * hash + Objects.hashCode(this.name);
            return hash;
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
            if (!Objects.equals(this.name, other.name)) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "Vertex{" + "name=" + name + '}';
        }
    }
    
    public static class Edge 
    {
        private final Integer distance;
        private final Vertex vertexFrom;
        private final Vertex vertexTo;

        public Edge(Integer distance, Vertex vertexFrom, Vertex vertexTo) {
            this.distance = distance;
            this.vertexFrom = vertexFrom;
            this.vertexTo = vertexTo;
        }
        
        public Integer getDistance() {
            return distance;
        }        

        public Vertex getVertexFrom() {
            return vertexFrom;
        }
        
        public Vertex getVertexTo() {
            return vertexTo;
        }
        
        @Override
        public String toString()
        {
            return this.vertexFrom + " -----> " + this.vertexTo + " (" + this.distance + ")";
        }
    }
    
    List<Edge> edgeList;
    
    public Graph() 
    {
        this.edgeList = new ArrayList<>();
    }
    
    public void addEdge(Edge n)
    {
        this.edgeList.add(n);
    }
    
    public static Graph buildGraphFromLogisticsNetwork(List<EntityLogisticsNetwork> network)
    {
        Graph g = new Graph();
        
        network.stream().forEach((path) -> {
            g.addEdge(new Edge(path.getDistance(), new Vertex(path.getSourceName()), new Vertex(path.getDestinyName())));
        });
        
        return g;
    }
    
    public void printGraph()
    {
        for (Edge e : this.edgeList)
        {
            System.out.println(e);
        }
    }
    
    /**
     * This method executes the Dijkstra's shortest path algorithm
     * @param vertexFrom the name of the vertex where the search should start
     * @param vertexTo the vertex that you want to get to 
     * @return if there`s a shortest path to <code>vertexTo</code>, it will return a graph with the shortest path <b>only</b>,
     * and will return <b>empty</b> graph (no edges) if there`s no shortest path to the <code>vertexTo</code> or if there`s no way to reach <code>vertexTo</code> 
     */
    public Graph shortestPathAlgorithm(String vertexFrom, String vertexTo)
    {
        Graph resultGraph = new Graph();
        
        //check that the vertexTo is in the map
        if (!this.edgeList.parallelStream().anyMatch(edge -> edge.getVertexTo().getName().equals(vertexTo)))
            return resultGraph;
        
        // find the possible start points in the graph
        List<Edge> possibleStartPoints = this.edgeList.parallelStream()
                .filter(edge -> edge.getVertexFrom().getName().equalsIgnoreCase(vertexFrom)).collect(Collectors.toList());
        
        if (possibleStartPoints.isEmpty())
            return resultGraph;
        
        possibleStartPoints.stream().forEach(System.out::println);
            
        return null;
    }
}