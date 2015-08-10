package com.walmart.delivery.optimization.business.test;

import business.Graph;
import business.Vertex;
import com.walmart.delivery.optimization.entities.EntityLogisticsNetwork;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author renannp
 */
public class GraphTest {
    
    @Test
    public void simpleGraphTest1()
    {
        List<EntityLogisticsNetwork> network = new ArrayList<>();
        
        network.add(new EntityLogisticsNetwork() {{
            setSourceName("RUA_W");
            setDestinyName("RUA_X");
            setDistance(10);
        }});
        network.add(new EntityLogisticsNetwork() {{
            setSourceName("RUA_X");
            setDestinyName("RUA_Y");
            setDistance(5);
        }});
        network.add(new EntityLogisticsNetwork() {{
            setSourceName("RUA_X");
            setDestinyName("RUA_Z");
            setDistance(7);
        }});        
        network.add(new EntityLogisticsNetwork() {{
            setSourceName("RUA_Y");
            setDestinyName("RUA_Z");
            setDistance(3);
        }});        
        
        Graph graph = Graph.buildGraphFromLogisticsNetwork(network);
        
        Stack<Vertex> shortestPath = graph.shortestPathAlgorithm("RUA_W", "RUA_Z");
        
        Assert.assertEquals("RUA_W", shortestPath.pop().getName());
        Assert.assertEquals("RUA_X", shortestPath.pop().getName());
        Assert.assertEquals("RUA_Z", shortestPath.pop().getName());
    }
    
    @Test
    public void simpleGraphTest2()
    {
        List<EntityLogisticsNetwork> network = new ArrayList<>();
        
        network.add(new EntityLogisticsNetwork() {{
            setSourceName("RUA_W");
            setDestinyName("RUA_X");
            setDistance(10);
        }});
        network.add(new EntityLogisticsNetwork() {{
            setSourceName("RUA_X");
            setDestinyName("RUA_Y");
            setDistance(5);
        }});
        network.add(new EntityLogisticsNetwork() {{
            setSourceName("RUA_X");
            setDestinyName("RUA_Z");
            setDistance(7);
        }});        
        network.add(new EntityLogisticsNetwork() {{
            setSourceName("RUA_Y");
            setDestinyName("RUA_Z");
            setDistance(1);
        }});        
        
        Graph graph = Graph.buildGraphFromLogisticsNetwork(network);
        
        Stack<Vertex> shortestPath = graph.shortestPathAlgorithm("RUA_W", "RUA_Z");
        
        Assert.assertEquals("RUA_W", shortestPath.pop().getName());
        Assert.assertEquals("RUA_X", shortestPath.pop().getName());
        Assert.assertEquals("RUA_Y", shortestPath.pop().getName());
        Assert.assertEquals("RUA_Z", shortestPath.pop().getName());
    }
    
    @Test
    public void noPathTest()
    {
        List<EntityLogisticsNetwork> network = new ArrayList<>();
        
        network.add(new EntityLogisticsNetwork() {{
            setSourceName("RUA_W");
            setDestinyName("RUA_X");
            setDistance(10);
        }});
        network.add(new EntityLogisticsNetwork() {{
            setSourceName("RUA_X");
            setDestinyName("RUA_Y");
            setDistance(5);
        }});
        network.add(new EntityLogisticsNetwork() {{
            setSourceName("RUA_X");
            setDestinyName("RUA_Z");
            setDistance(7);
        }});        
        network.add(new EntityLogisticsNetwork() {{
            setSourceName("RUA_Y");
            setDestinyName("RUA_Z");
            setDistance(1);
        }});  
    
        Graph graph = Graph.buildGraphFromLogisticsNetwork(network);
        
        Stack<Vertex> shortestPath = graph.shortestPathAlgorithm("RUA_W", "RUA_A");
        
        Assert.assertEquals(0, shortestPath.size());
    }
    
    @Test
    public void unconnectedTest()
    {
         List<EntityLogisticsNetwork> network = new ArrayList<>();
        
        network.add(new EntityLogisticsNetwork() {{
            setSourceName("RUA_A");
            setDestinyName("RUA_B");
            setDistance(10);
        }});
        network.add(new EntityLogisticsNetwork() {{
            setSourceName("RUA_A");
            setDestinyName("RUA_D");
            setDistance(3);
        }});
        network.add(new EntityLogisticsNetwork() {{
            setSourceName("RUA_C");
            setDestinyName("RUA_D");
            setDistance(5);
        }});
    
        Graph graph = Graph.buildGraphFromLogisticsNetwork(network);
        
        Stack<Vertex> shortestPath = graph.shortestPathAlgorithm("RUA_A", "RUA_C");
        
        Assert.assertEquals(0, shortestPath.size());
    }
}
