package com.walmart.delivery.optimization.rest.test;

import com.walmart.delivery.optimization.beans.EntityMapFacade;
import com.walmart.delivery.optimization.entities.EntityLogisticsNetwork;
import com.walmart.delivery.optimization.entities.EntityMap;
import com.walmart.delivery.optimization.rest.DeliveryOptimizationServices;
import com.walmart.delivery.optimization.rest.responses.ShortestPathResponse;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author renannp
 */
@RunWith(Arquillian.class)
public class TestAPI {

    @EJB
    private EntityMapFacade mapEjb;
    
    @Deployment
    public static WebArchive createDeployment() {
        
        return ShrinkWrap
                .create(WebArchive.class, "delivery-optimization-web-test.war")
                .addPackages(true, "com.walmart")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }
    
    private String testMap1 = "test map";
    
    @Test
    @InSequence(0)
    public void simpleMapInsertTest() throws Exception 
    {                
        EntityMap newMap = new EntityMap();
        newMap.setName(testMap1);

        // validate that the id is null
        Assert.assertNull(newMap.getId());
        
        // the map name should be always converted to upppercase 
        Assert.assertEquals(newMap.getName(), testMap1.toUpperCase());
     
        List<EntityLogisticsNetwork> network = new ArrayList<>();
        
        network.add(new EntityLogisticsNetwork("rua_w", "rua_x", 10, newMap));
        network.add(new EntityLogisticsNetwork("rua_x", "rua_y", 5, newMap));
        network.add(new EntityLogisticsNetwork("rua_y", "rua_z", 30, newMap));
        network.add(new EntityLogisticsNetwork("rua_w", "rua_a", 20, newMap));
        network.add(new EntityLogisticsNetwork("rua_a", "rua_z", 10, newMap));
        
        newMap.setLogisticsNetwork(network);
        
        // taking one network to test
        EntityLogisticsNetwork oneNet = network.get(0);
        
        Assert.assertNull(oneNet.getId());
        // should also be uppercase
        Assert.assertEquals("RUA_W", oneNet.getSourceName());
        Assert.assertEquals("RUA_X", oneNet.getDestinyName());
        assert 10 == oneNet.getDistance();
        
        // persist the map
        mapEjb.create(newMap);
        
        // after inserting into the database it should not be null anymore
        Assert.assertNotNull(newMap.getId());
        
        EntityMap find = mapEjb.find(newMap.getId());
        
        if (find != null) 
            testOutput(find.toString());
        else 
        {
            Assert.fail("The map was not found.");
            return; // putting return here to avoid warning in the below code
        }
        
        Assert.assertNotNull(find.getLogisticsNetwork());
        Assert.assertEquals(5, find.getLogisticsNetwork().size());
        
        // check that all the network paths were persisted
        for (EntityLogisticsNetwork n : network)
            Assert.assertNotNull(n.getId());
    }
    
    @Test
    @InSequence(1)
    public void getNetworkByNameTest() 
    {
        // try to find the map created in the test above
        List<EntityLogisticsNetwork> network = mapEjb.getNetworkByMapName(testMap1);
        
        Assert.assertEquals(5, network.size());
    }
    
    @Test
    @InSequence(value = 2)
    public void calculationRequestTest(@ArquillianResteasyResource DeliveryOptimizationServices deliveryServices)
    {
        ShortestPathResponse shortestPath = deliveryServices.getShortestPath(testMap1, "rua_w", "rua_z", 5, 3);
        
        testOutput(shortestPath.toString());
        
        // test that the path is right
        Assert.assertEquals("RUA_W", shortestPath.getShortestPath().get(0).getVertexName());
        Assert.assertEquals("RUA_A", shortestPath.getShortestPath().get(1).getVertexName());
        Assert.assertEquals("RUA_Z", shortestPath.getShortestPath().get(2).getVertexName());
        Assert.assertEquals("The total cost of this path should be 18.", 18f, shortestPath.getTotalCost(), 0);
    }
    
    public void testOutput(String... strings) {
        System.out.println("-------------------BEGIN TEST OUTPUT--------------------");
        System.out.println("--------------------------------------------------------");
        for (String s : strings)
            System.out.println(s);
        System.out.println("--------------------------------------------------------");
        System.out.println("-------------------END OF TEST OUTPUT-------------------");
    }
}
