package com.walmart.delivery.optimization.rest.test;

import com.walmart.delivery.optimization.beans.AbstractFacade;
import com.walmart.delivery.optimization.beans.EntityLogisticsNetworkFacade;
import com.walmart.delivery.optimization.beans.EntityMapFacade;
import com.walmart.delivery.optimization.config.JacksonConfig;
import com.walmart.delivery.optimization.entities.EntityLogisticsNetwork;
import com.walmart.delivery.optimization.entities.EntityMap;
import com.walmart.delivery.optimization.rest.ApplicationConfig;
import com.walmart.delivery.optimization.rest.DeliveryOptimizationServices;
import com.walmart.delivery.optimization.rest.responses.ResponseVertex;
import com.walmart.delivery.optimization.rest.responses.ShortestPathResponse;
import java.util.ArrayList;
import java.util.List;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author renannp
 */
@RunWith(Arquillian.class)
public class TestAPI {
    
//    @ArquillianResource(value = URL.class)
//    private URL deploymentURL;
    
    @Deployment
    public static WebArchive createDeployment() {
        
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "delivery-optimization-ejb-test.jar")
                .addClass(EntityMap.class)
                .addClass(AbstractFacade.class)
                .addClass(EntityLogisticsNetwork.class)
                .addClass(EntityLogisticsNetworkFacade.class)
                .addClass(EntityMapFacade.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");

        return ShrinkWrap
                .create(WebArchive.class, "delivery-optimization-web-test.war")
//                .addAsLibraries(jar)
//                .addClasses(JacksonConfig.class)
//                .addClasses(ApplicationConfig.class)
//                .addClasses(ShortestPathResponse.class)
//                .addClasses(ResponseVertex.class)
                .addPackages(true, "com.walmart")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
//                .addClasses(DeliveryOptimizationServices.class).addPackages(true, "com.walmart");
        
        //        addPackages(true, "com.project.beans", "com.project.model", "com.project.services")
        //https://github.com/arquillian/arquillian-extension-rest/tree/master/rest-client
    }
    
    @Test
    public void hu3hu3h3u3uh(@ArquillianResteasyResource DeliveryOptimizationServices customerResource) throws InterruptedException
    {
//        Given as;
//        Thread.sleep(1000 * 30);
        EntityMap entityMap = new EntityMap();
        
        entityMap.setName("testando123");
        
        List<EntityLogisticsNetwork> network = new ArrayList<>();
        network.add(new EntityLogisticsNetwork("a", "b", 312, entityMap));
        
        entityMap.setLogisticsNetwork(network);
        
        customerResource.registerLogisticsNetwork(entityMap);
        
        System.out.println(entityMap);
        System.out.println("brbr");
    }
}
