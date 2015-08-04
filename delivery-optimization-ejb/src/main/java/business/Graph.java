package business;

import com.walmart.delivery.optimization.entities.EntityLogisticsNetwork;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author renannp
 */
public class Graph 
{   
    public static class Vertex
    {
        private String name;

        public Vertex(String nodeName)
        {
            this.name = nodeName;
        }
        
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
            return "Node{" + "name=" + name + '}';
        }
    }
    
    public static class Edge 
    {
        private Integer distance;
        private Vertex vertexFrom;
        private Vertex vertexTo;

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        public Vertex getVertexFrom() {
            return vertexFrom;
        }

        public void setVertexFrom(Vertex vertexFrom) {
            this.vertexFrom = vertexFrom;
        }

        public Vertex getVertexTo() {
            return vertexTo;
        }

        public void setVertexTo(Vertex vertexTo) {
            this.vertexTo = vertexTo;
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
    
    public Graph shortestPathAgorithm(String nodeNameFrom, String nodeNameTo) 
    {
        return new Graph();
    }
    
    public static Graph buildGraphFromLogisticsNetwork(List<EntityLogisticsNetwork> network)
    {
        Graph g = new Graph();
        
        network.stream().forEach((edge) -> {
            g.addEdge(new Edge() 
            {{
                setVertexFrom(new Vertex(edge.getSourceName()));
                setVertexTo(new Vertex(edge.getDestinyName()));
                setDistance(edge.getDistance());
            }});
        });
        
        return g;
    }
    
    public void printGraph()
    {
        for (Edge e : this.edgeList)
        {
            System.out.println(e.vertexFrom + " -----> " + e.vertexTo + " (" + e.distance + ")");
        }
    }
    
    /**
     * This method executes the Dijkstra's shortest path algorithm
     * @param nodeFrom the name of the node where the search should start
     * @param nodeTo the node that you want to get to 
     * @return if there`s a shortest path to <code>vertexTo</code>, it will return a graph with the shortest path,
     * and will return <b>empty</b> graph (no edges) if there`s no shortest path to the <code>vertexTo</code>
     */
    public Graph shortestPathAlgorithm(String nodeFrom, String nodeTo)
    {
        Graph result = new Graph();
        
        return null;
    }
}