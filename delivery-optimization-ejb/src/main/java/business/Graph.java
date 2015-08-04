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
    public static class Node
    {
        private String nodeName;

        public Node(String nodeName)
        {
            this.nodeName = nodeName;
        }
        
        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }
        
        @Override
        public int hashCode() {
            int hash = 3;
            hash = 53 * hash + Objects.hashCode(this.nodeName);
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
            final Node other = (Node) obj;
            if (!Objects.equals(this.nodeName, other.nodeName)) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "Node{" + "nodeName=" + nodeName + '}';
        }
    }
    
    public static class Edge 
    {
        private Integer distance;
        private Node nodeFrom;
        private Node nodeTo;

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        public Node getNodeFrom() {
            return nodeFrom;
        }

        public void setNodeFrom(Node nodeFrom) {
            this.nodeFrom = nodeFrom;
        }

        public Node getNodeTo() {
            return nodeTo;
        }

        public void setNodeTo(Node nodeTo) {
            this.nodeTo = nodeTo;
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
                setNodeFrom(new Node(edge.getSourceName()));
                setNodeTo(new Node(edge.getDestinyName()));
                setDistance(edge.getDistance());
            }});
        });
        
        return g;
    }
    
    public void printGraph()
    {
        for (Edge e : this.edgeList)
        {
            System.out.println(e.nodeFrom + " -----> " + e.nodeTo + " (" + e.distance + ")");
        }
    }
    
}