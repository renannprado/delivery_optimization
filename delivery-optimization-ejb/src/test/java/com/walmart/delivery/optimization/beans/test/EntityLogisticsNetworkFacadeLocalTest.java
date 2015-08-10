package com.walmart.delivery.optimization.beans.test;

import com.walmart.delivery.optimization.beans.AbstractFacade;
import com.walmart.delivery.optimization.beans.EntityLogisticsNetworkFacade;
import com.walmart.delivery.optimization.beans.EntityMapFacade;
import com.walmart.delivery.optimization.entities.EntityLogisticsNetwork;
import com.walmart.delivery.optimization.entities.EntityMap;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author renannp
 */
@RunWith(Arquillian.class)
public class EntityLogisticsNetworkFacadeLocalTest {

    @EJB
    private EntityLogisticsNetworkFacade networkEjb;

    @EJB
    private EntityMapFacade mapEjb;

    @PersistenceContext
    EntityManager asd;

    @Resource
    UserTransaction ut;

    @Deployment
    public static JavaArchive createTestArchive() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClass(EntityMap.class)
                .addClass(AbstractFacade.class)
                .addClass(EntityLogisticsNetwork.class)
                .addClass(EntityLogisticsNetworkFacade.class)
                .addClass(EntityMapFacade.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
        testOutput(jar.toString(true));

        return jar;
    }
    
    @Before
    public void clean() throws Exception {
        ut.begin();
        asd.joinTransaction();
        Query network = asd.createNativeQuery("delete from TB_LOGISTICS_NETWORK ");
        Query map = asd.createNativeQuery("delete from TB_MAP");
        testOutput("network Deteled: " + network.executeUpdate(), "map Deteled: " + map.executeUpdate());
        ut.commit();
    }

    @Test
    public void shouldBeAbleToInjectEJB() throws Exception 
    {
        String mapName = "test map";
        EntityMap newMap = new EntityMap();
        newMap.setName(mapName);

        // validate that the id is null
        Assert.assertNull(newMap.getId());
        
        // the map name should be always converted to upppercase 
        Assert.assertEquals(newMap.getName(), mapName.toUpperCase());
        
        mapEjb.create(newMap);
        
        // after inserting into the database it should not be null anymore
        Assert.assertNotNull(newMap.getId());
        
        System.out.println(newMap);

        EntityMap find = mapEjb.find(newMap.getId());
        if (find != null) {
            System.out.println(networkEjb);
        } else {
            throw new NullPointerException();
        }
    }
    
    public static void testOutput(String... strings) {
        System.out.println("-------------------BEGIN TEST OUTPUT--------------------");
        System.out.println("--------------------------------------------------------");
        for (String s : strings)
            System.out.println(s);
        System.out.println("--------------------------------------------------------");
        System.out.println("-------------------END OF TEST OUTPUT-------------------");
    }
}
